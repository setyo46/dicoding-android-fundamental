package com.example.myflexiblefragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


/**
 * Mengirim Data Antar Fragment
 */
class DetailCategoryFragment : Fragment() {

    private lateinit var categoryNameTextView: TextView
    private lateinit var categoryDescriptionTextView: TextView
    private lateinit var toProfileButton: Button
    private lateinit var showDialogButton: Button

    var description: String? = null

    companion object {
        var EXTRA_NAME = "extra_name"
        var EXTRA_DESCRIPTION = "extra_description"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryNameTextView = view.findViewById(R.id.categoryNameTextViw)
        categoryDescriptionTextView = view.findViewById(R.id.categoryDescriptionTextView)
        toProfileButton = view.findViewById(R.id.toProfileButton)
        showDialogButton = view.findViewById(R.id.showDialogButton)

        /**
         * Mengapa pakai requireActivity() ? Padahal pada modul tentang Intent sebelumnya, kita menggunakan
         * this@MainActivity sebagai context. Hal ini karena kita menggunakan Fragment, sedangkan fungsi
         * this hanya bisa dipanggil melalui Activity. Oleh karena itulah, kita menggunakan requireActivity()
         * untuk mendapatkan context dari activity tempat fragment ini menempel.
         */
        toProfileButton.setOnClickListener{
            val mIntent = Intent(requireContext(), ProfileActivity::class.java)
            startActivity(mIntent)
        }

        showDialogButton.setOnClickListener{

            // object fragment
            val mOptionDialogFragment = OptionDialogFragment()

            /**
             *  getChildFragmentManager(), yakni sebuah nested fragment / fragment bersarang.
             *  Karena OptionDialogFragmentdipanggil di dalam sebuah obyek fragment yang telah
             *  ada sebelumnya yaitu DetailCategoryFragment, maka demi performa lebih baik. //DialogFragment()
             */
            val mFragmentManager = childFragmentManager
            mOptionDialogFragment.show(mFragmentManager, OptionDialogFragment::class.java.simpleName)
            // Baris di atas digunakan untuk menampilkan obyek OptionDialogFragment ke layar.

            /**
             * Untuk proses handling event ketika tombol Pilih pada OptionDialogFragment diklik,
             * kita menggunakan implementasi interface. // DialogFragment()
             */
        }

        /** menerima data dari fragment*/
        if (savedInstanceState != null ) {
            val descFromBundle = savedInstanceState.getString(EXTRA_DESCRIPTION) // with setter and getter

            description = descFromBundle
        }

        if (arguments != null ) {
            val categoryName = arguments?.getString(EXTRA_NAME) // with bundle

            categoryNameTextView.text = categoryName
            categoryDescriptionTextView.text = description
        }
    }
    /** Di sini kita menampillkan sebuah dialog ke pengguna untuk memilih sebuah opsi yang tersedia. //DialogFragment()*/
    internal var optionDialogListener: OptionDialogFragment.OnOptionDialogListener = object : OptionDialogFragment.OnOptionDialogListener {
        override fun onOptionChosen(text: String?) {
            Toast.makeText(requireActivity(), text, Toast.LENGTH_SHORT).show()
        }
    }
}