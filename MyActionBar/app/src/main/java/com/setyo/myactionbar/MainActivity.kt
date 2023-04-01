package com.setyo.myactionbar

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.setyo.myactionbar.databinding.ActivityMainBinding
import androidx.appcompat.widget.SearchView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    /**
     * untuk menampilkan custom item pada action bar
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

         /**
           * Untuk mengambil komponen searchview yang sebelumnya sudah di inflate,
           * kita menggunakan fungsi berikut menu.findItem().getActionView().
         */
        val searchView = menu.findItem(R.id.search).actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        /**
         * SetQueryHint() berguna untuk memberikan hint pada pengguna tentang query search apa yang telah dimasukkan.
         */
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            /*
                Gunakan method ini ketika search selesai atau OK
            */
            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(this@MainActivity, query, Toast.LENGTH_SHORT).show()
                /**
                 * clearFocus yang dipanggil supaya tidak ada duplikasi dalam pemanggilan fungsi onQueryTextSubmit.
                 */
                searchView.clearFocus()
                return true
            }
            /*
                Gunakan method ini untuk merespon tiap perubahan huruf pada searchView
            */
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menu1 -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.containerFragment, MenuFragment())
                    .addToBackStack(null)
                    .commit()
                true
            }
            R.id.menu2 -> {
                val i = Intent(this, MenuActivity::class.java)
                startActivity(i)
                true
            }
            else -> true
        }
    }
}