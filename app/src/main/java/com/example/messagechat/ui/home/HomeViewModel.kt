package com.example.messagechat.ui.home

import android.graphics.drawable.Drawable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.messagechat.databindingcomponent.ObservableViewModel

class HomeViewModel : ObservableViewModel() {

    private var bottomNavChatDrawable: Drawable? = null
    private var bottomNavChatTextColor: Int? = null
    private var bottomNavContactsDrawable: Drawable? = null
    private var bottomNavContactsTextColor: Int? = null
    private var isBottomNavChatSelected: Boolean = true
    /*private var isBottomNavChatSelected = MutableLiveData<Boolean>().apply {
        value = true
    }*/

    @Bindable
    fun getBottomNavChatDrawable() = bottomNavChatDrawable

    @Bindable
    fun getBottomNavChatTextColor() = bottomNavChatTextColor

    @Bindable
    fun getBottomNavContactsDrawable() = bottomNavContactsDrawable

    @Bindable
    fun getBottomNavContactsTextColor() = bottomNavContactsTextColor

    fun getIsBottomNavChatSelected() = isBottomNavChatSelected

    fun setBottomNavChatDrawable(drawable: Drawable) {
        bottomNavChatDrawable = drawable
        notifyPropertyChanged(BR.bottomNavChatDrawable)
    }

    fun setBottomNavChatColor(color: Int) {
        bottomNavChatTextColor = color
        notifyPropertyChanged(BR.bottomNavChatTextColor)
    }

    fun setBottomNavContactsDrawable(drawable: Drawable) {
        bottomNavContactsDrawable = drawable
        notifyPropertyChanged(BR.bottomNavContactsDrawable)
    }

    fun setBottomNavContactsTextColor(color: Int) {
        bottomNavContactsTextColor = color
        notifyPropertyChanged(BR.bottomNavContactsTextColor)
    }

    fun setIsBottomNavChatSelected(boolean: Boolean) {
        isBottomNavChatSelected = boolean
    }
}