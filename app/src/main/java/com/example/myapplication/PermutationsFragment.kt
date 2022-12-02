package com.example.myapplication


import android.annotation.SuppressLint
import android.opengl.Visibility
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.databinding.FragmentPermutationsBinding
import com.google.android.material.internal.ViewUtils


class PermutationsFragment : Fragment(R.layout.fragment_permutations) {
    private lateinit var binding: FragmentPermutationsBinding
    private var btnSwitchCheker = false
    @SuppressLint("RestrictedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentPermutationsBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            validate()
            view.setOnClickListener{
                ViewUtils.hideKeyboard(view)
            }
            btnSwitch.setOnClickListener {
                btnSwitchCheker = btnSwitch.isChecked
            }
            btnPermutationSubmit.setOnClickListener {
                if (!btnSwitchCheker) {
                    tvResult.text =
                        permutationWithOutRepeat(
                            etK.text.toString().toLong()

                        )

                }

            }
        }
    }


    fun validate() {
        binding.etN.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if (binding.etN.text.isNullOrBlank()
                    || binding.etN.text.toString() == "0"
                    || binding.etN.text.toString().toLong() > 20

                ) {
                    binding.etN.error = "введите нормальные числа"
                }

            }

            override fun afterTextChanged(s: Editable?) {
                if (binding.etN.text.isNullOrBlank()
                    || binding.etN.text.toString() == "0"
                    || binding.etN.text.toString().toLong() > 20

                ) {
                    binding.etN.error = "введите нормальные числа"
                    binding.btnPermutationSubmit.isEnabled = false

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
                    binding.etK.error = "введите нормальные числа"
                } else {
                    binding.btnPermutationSubmit.isEnabled = true
                }

            }

            override fun afterTextChanged(s: Editable?) {
                if (binding.etK.text.isNullOrBlank()
                    || binding.etK.text.toString() == "0"
                    || binding.etK.text.toString().toLong() > 20

                ) {
                    binding.etK.error = "введите нормальные числа"
                    binding.btnPermutationSubmit.isEnabled = false

                } else {
                    binding.btnPermutationSubmit.isEnabled = true
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

    private fun permutationWithOutRepeat(kNum: Long): String {
        return factorial(kNum).toString()
    }

}
