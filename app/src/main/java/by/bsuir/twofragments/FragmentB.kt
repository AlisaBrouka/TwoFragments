package by.bsuir.twofragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.bsuir.twofragments.databinding.FragmentBBinding
import java.lang.Integer.parseInt

class FragmentB : Fragment() {
    private var _binding: FragmentBBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentBBinding.inflate(inflater, container, false)
        val view = binding.root

        val args = arguments
        if (args != null){
            updateTextCounter(args.getInt("counter"))
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun updateTextCounter(value: Int){
        binding.textViewCounter.text = value.toString()
    }
}