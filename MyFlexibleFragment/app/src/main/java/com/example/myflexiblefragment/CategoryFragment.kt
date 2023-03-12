package com.example.myflexiblefragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class CategoryFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val detailCategoryButton: Button = view.findViewById(R.id.detailCategoryButton)
        detailCategoryButton.setOnClickListener(this)
    }

    override fun onClick(v: View) {

        if (v.id == R.id.detailCategoryButton) {

            // object fragment
            val mDetailCategoryFragment = DetailCategoryFragment()

            /**
             * menggunakan obyek bundle untuk mengirimkan data antar fragment.
             * cara yang digunakan sama dengan cara yang telah kita implementasikan sebelumnya di activity.
             */
            val mBundle = Bundle()
            mBundle.putString(DetailCategoryFragment.EXTRA_NAME, "lifestyle") // with bundle

            val description = "Kategori ini akan berisi produk-produk lifestyle" // with setter and getter

            mDetailCategoryFragment.arguments = mBundle // with bundle
            mDetailCategoryFragment.description = description // with setter and getter

            // parentFragmentManager untuk mendapatkan FragmentManager dari Activity.
            val mFragmentManager = parentFragmentManager

            mFragmentManager?.beginTransaction()?.apply {
                replace(R.id.frame_container, mDetailCategoryFragment, DetailCategoryFragment::class.java.simpleName)
                addToBackStack(null)
                commit()
            }

        }
    }


}