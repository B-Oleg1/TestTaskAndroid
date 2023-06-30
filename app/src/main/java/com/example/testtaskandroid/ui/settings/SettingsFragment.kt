package com.example.testtaskandroid.ui.settings

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.testtaskandroid.databinding.FragmentSettingsBinding
import com.example.testtaskandroid.fetchData.ConfigureSettings
import com.example.testtaskandroid.fetchData.FetchData
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

        initUIValues()

        binding.editText1.addTextChangedListener {
            onUpdateSettingsValues()
        }
        binding.editText2.addTextChangedListener {
            onUpdateSettingsValues()
        }
        binding.editText3.addTextChangedListener {
            onUpdateSettingsValues()
        }

        binding.settingsButtonCheckConnection.setOnClickListener {
            FetchData().checkServerAvailability(::onUpdateStatusServer).start()
        }

        return binding.root
    }

    private fun initUIValues() {
        if (ConfigureSettings.configureSettingsInfo != null) {
            binding.editText1.setText(ConfigureSettings.configureSettingsInfo?.firstParam)
            binding.editText2.setText(ConfigureSettings.configureSettingsInfo?.secondParam)
            binding.editText3.setText(ConfigureSettings.configureSettingsInfo?.thirdParam)
        }
        if (ConfigureSettings.statusConnectionInfo != null) {
            binding.settingsTextLastCheck.setText("Последняя проверка: " +
                    "${ConfigureSettings.statusConnectionInfo?.timeUpdate}")

            val backgroundTintList = if (ConfigureSettings.statusConnectionInfo?.isAvailable!!) {
                ColorStateList.valueOf(Color.rgb(37, 193, 3))
            } else {
                ColorStateList.valueOf(Color.rgb(234, 9, 9))
            }
            binding.settingsButtonConnectionStatus.backgroundTintList = backgroundTintList
        }
    }

    private fun onUpdateSettingsValues() {
        ConfigureSettings.updateConfigureSettingsFile(binding.editText1.text.toString(),
                                                      binding.editText2.text.toString(),
                                                      binding.editText3.text.toString())
    }

    private fun onUpdateStatusServer(isAvailable: Boolean) {
        val dateTime = LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))
        ConfigureSettings.updateStatusConnectionFile(isAvailable, dateTime)

        activity?.runOnUiThread {
            initUIValues()
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}