package com.nanda.dynamicbaseurl

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.nanda.dynamicbaseurl.databinding.ActivityMainBinding
import com.nanda.dynamicbaseurl.entity.BaseUrlType
import com.nanda.dynamicbaseurl.utils.Extensions.loadImage
import com.nanda.dynamicbaseurl.utils.SharedPreferencesManager

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var prefsManager: SharedPreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
        initView()
        setupObservers()
    }

    private fun initData() {
        prefsManager = SharedPreferencesManager(this)
        viewModel.fetchData()
    }

    private fun initView() = with(binding) {
        updateTextInfo()
        checkSwitch()

        btnExecute.setOnClickListener {
            viewModel.fetchData()
        }
        switchUrl.setOnCheckedChangeListener { _, isChecked ->
            switchBaseUrl(isChecked)
        }
    }

    private fun switchBaseUrl(isChecked: Boolean) {
        prefsManager.saveBaseUrl(
            if (isChecked) 1 else 0
        )
    }

    private fun checkSwitch() {
        val urlType = prefsManager.getBaseUrlType()
        val isSwitchChecked = urlType == BaseUrlType.DOG.id

        binding.switchUrl.isChecked = isSwitchChecked
    }

    private fun updateTextInfo() {
        val baseUrlTitle = BaseUrlType.getTitleById(prefsManager.getBaseUrlType())
        binding.tvApiInfo.text = "You're calling $baseUrlTitle API"
    }

    private fun setupObservers() {
        viewModel.imageUrl.observe(this) { img ->
            updateTextInfo()
            binding.ivResponse.loadImage(this, img, R.drawable.bg_placeholder)
        }

        viewModel.error.observe(this) { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }
}