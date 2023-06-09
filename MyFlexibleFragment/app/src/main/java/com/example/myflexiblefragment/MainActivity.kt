package com.example.myflexiblefragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         /**
          * FragmentManager yang merupakan antarmuka untuk mengorganisir objek
          * fragment yang terdapat didalam sebuah activity.
         */
        val mFragmentManager = supportFragmentManager

        // object fragment
        val mHomeFragment = HomeFragment()

        /**
         * FragmentTransaction merupakan fungsi untuk melakukan operasi pada
         * fragment seperti add(), replace(), commit() dsb.
         */

        val fragment = mFragmentManager.findFragmentByTag(HomeFragment::class.java.simpleName)

        if (fragment !is HomeFragment) {
            Log.d("MyFlexibleFragment", "Fragment Name :" + HomeFragment::class.java.simpleName)
            mFragmentManager
                .beginTransaction()
                .add(R.id.frame_container, mHomeFragment, HomeFragment::class.java.simpleName)
                .commit()

            /**
             * Metode .beginTransaction() untuk memulai proses perubahan
             * Metode add() akan menambahkan objek fragment ke dalam layout container
               Layout container ini merupakan objek framelayout dengan ID frame_container
             * Metode .commit() di atas akan mengeksekusi untuk melakukan pemasangan objek secara asynchronous
               untuk ditampilkan ke antar muka pengguna (user interface).
             */
        }
    }

}

