package com.example.madcampweek1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.madcampweek1.databinding.FragmentPictureBinding
import android.widget.GridView
import android.widget.Toast

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.core.app.ActivityCompat.startActivityForResult

import android.Manifest
import android.content.ContentUris
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.appcompat.app.AppCompatActivity

import android.database.Cursor
import android.util.Log
import java.util.ArrayList

class Picture : Fragment() {
    private lateinit var binding: FragmentPictureBinding

    private val names = ArrayList<String>()
    private val images = ArrayList<String>()
    private lateinit var gridAdapter: GridAdapter

    // 갤러리에서 이미지를 선택할 때 사용할 상수
    private val GALLERY_PERMISSION_REQUEST = 123

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPictureBinding.inflate(inflater, container, false)

        if (checkGalleryPermission()) {
            loadImages()
        } else {
            // TODO: permission 요청하고 나서 loadImage 불러올 수 있도록 하기
            // onDestroyView 한 후 onCreateView 호출
            requestGalleryPermission()
        }

        return binding.root
    }

    private fun checkGalleryPermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { // vresion이 marshmallow 이상
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED // permission 확인
        } else { // version이 marshmallow 미만인 경우 항상 true
            true
        }
    }

    private fun requestGalleryPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            GALLERY_PERMISSION_REQUEST // 권한 요청 결과 구분/처리 위한 상수
        )
        
    }

    private fun loadImages() {
        // 갤러리에서 이미지 가져오기
        val projection = arrayOf(
            MediaStore.Images.Media.DATA, // 이미지 실제 파일 경로 array
            MediaStore.Images.Media.DISPLAY_NAME) // 이미지 파일 이름
        val cursor = requireActivity().contentResolver.query( // contentResolver: 앱이 data에 접근하기 위한 interface 역할
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, // 이미지 URI. 메타데이터 포함 다양한 정보
            projection, // 가져올 data의 column
            "${MediaStore.Images.Media.DISPLAY_NAME} LIKE ?",
            arrayOf("student%"), // ?에 들어갈 내용. student로 시작하는 cf) array로 넣어야 함
            null // 정렬
        )

        cursor?.use {
            // ?: nullable, cursor: rows of results data from query(image info)
            // it: each row, use: using cursor safely and close

            val columnIndex1 = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            // cursor에서 사용할 column index, 여기서 실제 경로
            val columnIndex2 = it.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)

            while (it.moveToNext()) { // each row
                val imagePath = it.getString(columnIndex1) // 각 행의 해당 열(실제 경로)
                images.add(imagePath)
                val imageName = it.getString(columnIndex2)
                names.add(imageName.split("_")[1])
            }

            // 이미지를 표시하는 GridView에 Adapter 설정
            gridAdapter = GridAdapter(requireActivity(), names, images)
            binding.gridView.adapter = gridAdapter
        }
    }


}
