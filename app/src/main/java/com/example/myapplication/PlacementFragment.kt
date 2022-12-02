package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.View
import com.example.myapplication.databinding.FragmentPlacementBinding
import com.google.android.material.internal.ViewUtils
import kotlin.math.pow

class PlacementFragment : Fragment(R.layout.fragment_placement) {

    private lateinit var binding: FragmentPlacementBinding
    private var btnSwitchCheker = false
    @SuppressLint("RestrictedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentPlacementBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            validate()
            view.setOnClickListener{
                ViewUtils.hideKeyboard(view)
            }
            btnSwitch.setOnClickListener {
                btnSwitchCheker = btnSwitch.isChecked
            }

            btnPlacementSubmit.setOnClickListener {
                if (btnSwitchCheker) {
                    tvResult.text =
                        placementWithRepeat(
                            etN.text.toString().toDouble(),
                            etK.text.toString().toDouble()
                        )

                } else {
                    tvResult.text =
                        placementWithOutRepeat(
                            etN.text.toString().toLong(),
                            etK.text.toString().toLong()
                        )
                }
            }
        }

    }


    private fun placementWithOutRepeat(nNum: Long, kNum: Long): String {
        val kNumFactorial = factorial(kNum)
        val divisionFactorial = factorial(kNum - nNum)
        return (kNumFactorial / divisionFactorial).toString()
    }

    private fun placementWithRepeat(n_num: Double, k_num: Double): String {
        val result = k_num.pow(n_num).toLong()
        return result.toString()
    }


    private fun validate() {
        binding.etN.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if (binding.etN.text.isNullOrBlank()
                    || binding.etN.text.toString() =="0"
                    || binding.etN.text.toString().toLong() > 20

                ) {
                    binding.etN.error = "введите число"
                }else{
                    binding.etK.isEnabled= true
                }

            }

            override fun afterTextChanged(s: Editable?) {
                if (binding.etN.text.isNullOrBlank()
                    || binding.etN.text.toString() =="0"
                    || binding.etN.text.toString().toLong() > 20

                ) {
                    binding.etK.isEnabled= false
                    binding.etN.error = "введите число"
                    binding.btnPlacementSubmit.isEnabled = false

                }else{
                    binding.etK.isEnabled= true
                    if (!binding.etK.text.isNullOrBlank() && binding.etK.text.toString().toLong()<=20)
                        binding.btnPlacementSubmit.isEnabled = true
                }

            }

        })
        binding.etK.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if (binding.etK.text.isNullOrBlank()
                    || binding.etK.text.toString() =="0"
                    || binding.etK.text.toString().toLong() > 20


                ) {
                    binding.etK.error = "введите введите число большее или равное первому"
                } else {
                    if (!binding.etN.text.isNullOrBlank() && binding.etN.text.toString().toLong()<binding.etK.text.toString().toLong())
                        binding.btnPlacementSubmit.isEnabled = true
                    else{
                        binding.etK.error = "введите введите число большее" +
                                " или равное первому меньшее 20"
                        binding.btnPlacementSubmit.isEnabled = false
                    }

                }

            }

            override fun afterTextChanged(s: Editable?) {
                if (binding.etK.text.isNullOrBlank()
                    || binding.etK.text.toString() =="0"
                    || binding.etK.text.toString().toLong() > 20


                ) {
                    binding.etK.error = "введите введите число большее или равное первому"
                    binding.btnPlacementSubmit.isEnabled = false

                }else{
                    if (!binding.etN.text.isNullOrBlank() && binding.etN.text.toString().toLong()<binding.etK.text.toString().toLong())
                        binding.btnPlacementSubmit.isEnabled = true
                    else{
                        binding.etK.error = "введите введите число большее" +
                                " или равное первому меньшее 20"
                        binding.btnPlacementSubmit.isEnabled = false
                    }
                }
            }

        })
    }

    private fun factorial(num: Long): Long {
        var factorial: Long = 1
        for (i in 1..num) {
            factorial *= i
        }
        return factorial
    }
}
