package by.bsuir.twofragments

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import by.bsuir.twofragments.databinding.ActivityMainBinding

class MainActivity : FragmentActivity(), FragmentA.OnButtonClickedListener{

    private lateinit var binding: ActivityMainBinding
    private var counter: Int = 0
    private var fragmentB = FragmentB()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if(savedInstanceState != null){
            counter = savedInstanceState.getInt("counter")
        }

        val fragmentManager = supportFragmentManager
        /*replace, not add
        otherwise after rotate to landscape - rotate to portrait - rotate to landscape
        fragment a would be with number, not with button
        (we replace A with B in portrait below)*/
        fragmentManager.beginTransaction().replace(R.id.fragmentA, FragmentA()).addToBackStack(null).commit()
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            val args = Bundle()
            args.putInt("counter", counter)
            fragmentB.arguments = args
            fragmentManager.beginTransaction().replace(R.id.fragmentB, fragmentB).addToBackStack(null).commit()
        }
    }

    override fun onButtonClicked() {
        counter++
        val fragmentB = supportFragmentManager.findFragmentById(R.id.fragmentB) as FragmentB?
        /*check the orientation or
        after rotate to landscape - rotate to portrait
        fragment B is not being shown
        */
        if(fragmentB != null && resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            supportFragmentManager.beginTransaction().replace(R.id.fragmentB, fragmentB).addToBackStack(null).commit()
            fragmentB.updateTextCounter(counter)
        } else{
            val newFragmentB = FragmentB()
            val args = Bundle()
            args.putInt("counter", counter)
            newFragmentB.arguments = args
            supportFragmentManager.beginTransaction().replace(R.id.fragmentA, newFragmentB).addToBackStack(null).commit()
        }
    }

    override fun onAttachFragment(fragment: Fragment){
        if (fragment is FragmentA){
            fragment.setOnButtonClickedListener(this)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("counter", counter)
        super.onSaveInstanceState(outState)
    }
}
