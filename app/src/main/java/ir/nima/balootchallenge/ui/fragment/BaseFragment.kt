package ir.nima.balootchallenge.ui.fragment

import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import ir.nima.balootchallenge.R

@AndroidEntryPoint
open class BaseFragment(@LayoutRes layout: Int): Fragment(layout) {
    fun showLoading(progressBar: ProgressBar){
        progressBar.visibility = View.VISIBLE
    }

    fun hideLoading(progressBar: ProgressBar){
        progressBar.visibility = View.GONE
    }

    fun displayLoading(showLoading: Boolean, progressBar: ProgressBar){
        if (showLoading) showLoading(progressBar)
        else hideLoading(progressBar)
    }
    fun displayError(message: String?){
        if (message != null) Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        else Toast.makeText(requireContext(), getString(R.string.unknown_error), Toast.LENGTH_SHORT).show()
    }
    fun showToast(message: String?){
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
    fun isNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (cm != null) {
            val activeNetwork = cm.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting
        }
        return false
    }
}