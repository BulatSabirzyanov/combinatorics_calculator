package com.example.myapplication

import android.annotation.SuppressLint

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View

import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentCombinationBinding
import com.google.android.material.internal.ViewUtils.hideKeyboard


class CombinationFragment : Fragment(R.layout.fragment_combination) {
    private lateinit var binding: FragmentCombinationBinding
    private var btnSwitchCheker = false
    @SuppressLint("RestrictedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentCombinationBinding.bind(view)

        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            validate()
            view.setOnClickListener{
                hideKeyboard(view)
            }
            btnSwitch.setOnClickListener {
                btnSwitchCheker = btnSwitch.isChecked
            }

            btnCombinationSubmit.setOnClickListener {
                if (btnSwitchCheker) {
                    tvResult.text =
                        combinationWithRepeat(
                            etN.text.toString().toLong(),
                            etK.text.toString().toLong()
                        )

                } else {
                    tvResult.text =
                        combinationWithOutRepeat(
                            etN.text.toString().toLong(),
                            etK.text.toString().toLong()
                        )
                }
            }
        }
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
                    binding.etN.error = "введите нормальные числа"
                }else{
                    binding.etK.isEnabled= true
                }

            }

            override fun afterTextChanged(s: Editable?) {
                if (binding.etN.text.isNullOrBlank()
                    || binding.etN.text.toString() =="0"
                    || binding.etN.text.toString().toLong() > 20

                ) {
                    binding.etN.error = "введите нормальные числа"
                    binding.btnCombinationSubmit.isEnabled = false
                    binding.etK.isEnabled= false



                }else{
                    binding.etK.isEnabled= true
                    if (!binding.etK.text.isNullOrBlank() && binding.etK.text.toString().toLong()<=20)
                        binding.btnCombinationSubmit.isEnabled = true
                }

            }

        })
        binding.etK.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if (binding.etK.text.isNullOrBlank()
                    || binding.etK.text.toString() == "0"
                    || binding.etK.text.toString().toLong() > 20


                ) {
                    binding.etK.error = "введите введите число большее" +
                            " или равное первому меньшее 20"
                } else {
                    if (!binding.etN.text.isNullOrBlank() && binding.etN.text.toString().toLong()<binding.etK.text.toString().toLong())
                        binding.btnCombinationSubmit.isEnabled = true
                    else{
                        binding.etK.error = "введите введите число большее" +
                                " или равное первому меньшее 20"
                        binding.btnCombinationSubmit.isEnabled = false
                    }
                }

            }

            override fun afterTextChanged(s: Editable?) {
                if (binding.etK.text.isNullOrBlank()
                    || binding.etK.text.toString() =="0"
                    || binding.etK.text.toString().toLong() > 20
                ) {
                    binding.etK.error = "введите введите число большее" +
                            " или равное первому меньшее 20"
                    binding.btnCombinationSubmit.isEnabled = false

                }else{
                    if (!binding.etN.text.isNullOrBlank() && binding.etN.text.toString().toLong()<binding.etK.text.toString().toLong())
                        binding.btnCombinationSubmit.isEnabled = true
                    else{
                        binding.etK.error = "введите введите число большее" +
                                " или равное первому меньшее 20"
                        binding.btnCombinationSubmit.isEnabled = false
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

    private fun combinationWithOutRepeat(nNum: Long, kNum: Long): String {

        val nNumFactorial = factorial(nNum)
        val kNumFactorial = factorial(kNum)
        val divisionFactorial = factorial(kNum - nNum)

        val result = kNumFactorial / (nNumFactorial * divisionFactorial)
        println(nNumFactorial)
        println(kNumFactorial)
        println(divisionFactorial)
        return result.toString()
    }

    private fun combinationWithRepeat(nNum: Long, kNum: Long): String {
        return combinationWithOutRepeat(nNum, kNum + nNum - 1)
    }

}