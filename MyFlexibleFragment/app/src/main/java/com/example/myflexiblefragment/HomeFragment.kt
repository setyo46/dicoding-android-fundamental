package com.example.myflexiblefragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class HomeFragment : Fragment(), View.OnClickListener {

    /**
     * metode onCreateView() di mana layout interface didefinisikan dan ditransformasikan
     * dari layout berupa file xml ke dalam objek view dengan menggunakan metode inflate().
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        /**
         * Inflater.inflate() merupakan objek dari LayoutInflater yang berfungsi untuk mengubah
         * layout xml ke dalam bentuk objek viewgroup atau widget melalui pemanggilan metode inflate().
         * Sama seperti setContentView pada Activity, fungsi inflate di sini yaitu untuk menampilkan
         * layout dari Fragment, di mana layout yang ditampilkan di sini yaitu fragment_home.
         */
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    /**
     * metode onViewCreated() yang akan bekerja setelah metode onCreateView(). Di sini kita bisa
     * gunakan untuk inisialisasi view, dan juga mengatur action-nya (set listener).
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         *  Kode tersebut menandakan btn_category berada pada objek view di mana objek view berasal
         *  dari konversi fragment_home.xml. Bila hanya findViewById(R.id.btn_category),
         *  maka btn_category berada pada root layout, activity_main.xml.
         */
        val categoryButton: Button = view.findViewById(R.id.categoryButton)
        categoryButton.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if(v.id == R.id.categoryButton) {

            // object fragment
            val mCategoryFragment = CategoryFragment()

            /**
             * Berbeda dengan Activity yang memanfaatkan supportFragmentManager,
             * Fragment menggunakan parentFragmentManager untuk mendapatkan FragmentManager dari Activity.
             */
            val mFragmentManager = parentFragmentManager

            /**
             * method replace() akan mengganti objek fragment yang sedang tampil saat ini,
             * yaitu HomeFragment dengan objek fragment yang baru, yaitu CategoryFragment.
             */
            mFragmentManager.beginTransaction().apply {
                replace(R.id.frame_container, mCategoryFragment, CategoryFragment::class.java.simpleName)
                .addToBackStack(null)
                .commit()

                /** Nantinya ketika kita tekan tombol back, ia akan pop-out keluar dari stack
                 * .addToBackStack(null) dan menampilkan objek fragment sebelumnya HomeFragment. */
            }

        }
    }
}