package com.example.reactivepractise

import android.os.Bundle
import android.view.ViewGroup
import com.example.reactivepractise.databinding.ToolBarBindingLayout
import com.example.reactivepractise.fragment.base.BaseFragment
import com.example.reactivepractise.listing.view.ListFragment

class MainActivity : BaseActivity() {

    private val toolbarContainer: ViewGroup by lazy {
        findViewById<ViewGroup>(R.id.toolbar)
    }

    private val toolbarBinding: ToolBarBindingLayout by lazy {
        ToolBarBindingLayout.inflate(layoutInflater, toolbarContainer, true)
    }

    private fun getCurrentFragment() = supportFragmentManager.findFragmentById(R.id.container) as? BaseFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.addOnBackStackChangedListener {
            manageToolBar(getCurrentFragment())
        }

        val newFragment = ListFragment()
        val ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.container, newFragment).addToBackStack(newFragment.tag).commit()
    }

    private fun manageToolBar(fragment: BaseFragment?) {
        toolbarBinding.screenTitle = fragment?.provideScreenTitle() ?: ""
        toolbarBinding.executePendingBindings()
    }

}