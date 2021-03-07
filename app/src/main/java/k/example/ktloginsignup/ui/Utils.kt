package k.example.ktloginsignup.ui

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import k.example.ktloginsignup.data.network.Resource
import k.example.ktloginsignup.ui.auth.LogInFragment
import k.example.ktloginsignup.ui.base.BaseFragment

fun<A : Activity> Activity.startNewActivity(activity: Class<A>){
    Intent(this,activity).also {
        it.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }
}

fun View.visible(isVisible : Boolean){
    visibility=if (isVisible)View.VISIBLE else View.GONE
}

fun View.enable(enabled : Boolean){
   isEnabled=enabled
    alpha=if (enabled) 1f else 0.5f
}

fun View.snackbar(message: String, action: (() -> Unit)? = null) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    action?.let {
        snackbar.setAction("Retry") {
            it()
        }
    }
    snackbar.show()
}

fun Fragment.handleApiError(
        failure: Resource.Failure,
        retry: (() -> Unit)? = null
) {
    when {
        failure.isNetwork -> requireView().snackbar(
                "Please check your internet connection",
                retry
        )
        failure.errorCode == 401 -> {
            if (this is LogInFragment) {
                requireView().snackbar("You've entered incorrect email or password")
            } else {
                (this as BaseFragment<*, *, *>).logout()
            }
        }
        else -> {
            val error = failure.errorBody?.string().toString()
            requireView().snackbar(error)
        }
    }
}