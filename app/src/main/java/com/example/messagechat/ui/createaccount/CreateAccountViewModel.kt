package com.example.messagechat.ui.createaccount

import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.databinding.Bindable
import com.example.messagechat.BR
import com.example.messagechat.R
import com.example.messagechat.databindingcomponent.ObservableViewModel

class CreateAccountViewModel : ObservableViewModel() {
    private var userAvatar: Drawable? = null
    private var userAvatarColor: Int? = null

    private var avatarColor = "[0.5, 0.5, 0.5, 1]" //convert để save vào DB cho match với iOS (vì iOS rgb chỉ 0 - 1 thay vì 0 - 255)

    @Bindable
    fun getUserAvatar(): Drawable? {
        return userAvatar
    }

    fun setUserAvatar(drawable: Drawable) {
        userAvatar = drawable

        notifyPropertyChanged(BR.userAvatar)
    }

     @Bindable
    fun getUserAvatarColor(): Int? {
        return userAvatarColor
    }

     fun setUserAvatarColor(color: Int) {
        userAvatarColor = color

        notifyPropertyChanged(BR.userAvatarColor)
    }

}