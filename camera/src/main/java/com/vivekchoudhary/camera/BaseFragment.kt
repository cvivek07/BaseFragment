package com.vivekchoudhary.camera

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/**
 * it is base fragment of other existing fragment.
 */
open class BaseFragment : Fragment() {

    var TAG = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "fragment started: " + this.javaClass.simpleName)
    }


    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    /**
     * push nested fragment into provided container. it is hide/show the fragment. add/replace handle by isAdd parameter.
     * isBackStack allow you to add into backstack of fragment.
     */

    fun pushChildFragment(
        container: Int,
        parentFragment: Fragment,
        fragment: Fragment,
        isAdd: Boolean,
        isBackStack: Boolean
    ) {

        val name = fragment.javaClass.simpleName


        val ft = parentFragment.childFragmentManager.beginTransaction()
        if (isAdd) {
            ft.add(container, fragment, name)
        } else {
            ft.replace(container, fragment, name)
        }

        if (isBackStack) {
            ft.addToBackStack(null)
        }

        if (isAdd) {
            val currentFragment = parentFragment.childFragmentManager.findFragmentById(container)
            if (currentFragment != null) {
                ft.hide(currentFragment)
            }
        }

        ft.commit()
    }


    open fun isFragmentInBackstack(
        fragmentManager: FragmentManager,
        fragmentTagName: String
    ): Boolean {
        for (entry in 0 until fragmentManager.backStackEntryCount) {
            if (fragmentTagName == fragmentManager.getBackStackEntryAt(entry).javaClass.simpleName) {
                return true
            }
        }
        return false
    }

}