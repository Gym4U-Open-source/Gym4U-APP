package com.example.gym4u_movile_app

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.annotation.IdRes
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.gym4u_movile_app.databinding.ActivityMainBinding
import com.example.gym4u_movile_app.databinding.DialogLogoutBinding
import com.example.gym4u_movile_app.enums.Roles
import com.example.gym4u_movile_app.ui.login.LoginActivity
import com.example.gym4u_movile_app.util.AppPreferences
import com.example.gym4u_movile_app.util.AppPreferences.Companion.preferences
import com.example.gym4u_movile_app.util.UtilFn.Companion.startActivityAndClean

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var logoutBinding: DialogLogoutBinding
    private lateinit var logoutDialog: AlertDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        logoutBinding = DialogLogoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_clients,
                R.id.navigation_inbox,
                R.id.navigation_posts,
                R.id.navigation_library,
                R.id.navigation_community,
                R.id.navigation_conversation
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        initLogout()

    }

    private fun initLogout() {
        logoutBinding.btLogout.setOnClickListener {
            preferences.clean()
            startActivityAndClean(LoginActivity::class.java)
        }
        logoutBinding.btCancel.setOnClickListener { logoutDialog.dismiss() }
        logoutDialog = AlertDialog.Builder(this)
            .setView(logoutBinding.root)
            .create()
        logoutDialog.window?.setBackgroundDrawableResource(R.drawable.dialog_logout)
    }

    override fun onResume() {
        val roles = preferences.getUser().roles
        super.onResume()
        if(roles.contains(Roles.NORMAL.name) && !(roles.contains(Roles.ADMIN.name))) {
            occultMenuItem(R.id.navigation_library)
            occultMenuItem(R.id.navigation_clients)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logout -> {
                logoutDialog.show()
                true
            }
            else -> false
        }
    }

    private fun occultMenuItem(@IdRes id: Int) = binding.navView.menu.findItem(id).setVisible(false)

    fun enableBottomNavigation() {
        binding.navView.isEnabled = true
    }
}