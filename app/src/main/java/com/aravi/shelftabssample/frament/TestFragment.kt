package com.aravi.shelftabssample.frament

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aravi.shelftabssample.R
import kotlinx.android.synthetic.main.fragment_test.*

class TestFragment : Fragment() {

    var type = "page"
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_test, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        if (bundle != null) {
            if (bundle.containsKey("_type")) {
                type = bundle.getString("_type").toString()
            }
        }
        tvTag.text = type
    }
}