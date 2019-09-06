package com.daya.myactionbar

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.SearchView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchManager.let {sm ->
            val searchView = menu?.findItem(R.id.search)?.actionView as SearchView
            searchView.apply {
                setSearchableInfo(sm.getSearchableInfo(componentName))
                queryHint = resources.getString(R.string.search)
            }

            searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    Toast.makeText(this@MainActivity, query, Toast.LENGTH_SHORT).show();
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean = false
            })
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu1 ->{
                val menuFragment = MenuFragment()
                val fragmentManager = supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.apply {
                    replace(R.id.mainFragmentContainer, menuFragment)
                    addToBackStack(null)
                    commit()
                }
                return true
            }
            R.id.menu2 ->{
                val intent = Intent(this@MainActivity, MenuActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> return true
        }
    }
}
