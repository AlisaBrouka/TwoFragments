package by.bsuir.twofragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.bsuir.twofragments.databinding.FragmentABinding

class FragmentA : Fragment() {

    private var _binding: FragmentABinding? = null
    private val binding get() = _binding!!
    private lateinit var callback: OnButtonClickedListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentABinding.inflate(inflater, container, false)
        val view = binding.root

        binding.button.setOnClickListener{
            callback.onButtonClicked()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface OnButtonClickedListener{
        fun onButtonClicked()
    }

    fun setOnButtonClickedListener(callback: OnButtonClickedListener){
        this.callback = callback
    }
}