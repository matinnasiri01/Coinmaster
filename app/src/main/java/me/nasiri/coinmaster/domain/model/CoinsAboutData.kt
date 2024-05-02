package me.nasiri.coinmaster.domain.model


import android.os.Parcel
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import kotlinx.parcelize.Parceler

class CoinsAboutData : ArrayList<CoinsAboutData.CoinsAboutDataItem>() {
    @Parcelize
    data class CoinsAboutDataItem(
        @SerializedName("currencyName")
        val currencyName: String?,
        @SerializedName("info")
        val info: Info?,
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readParcelable(Info::class.java.classLoader)
        )

        @Parcelize
        data class Info(
            @SerializedName("desc")
            val desc: String?,
            @SerializedName("forum")
            val forum: String?,
            @SerializedName("github")
            val github: String?,
            @SerializedName("reddit")
            val reddit: String?,
            @SerializedName("twt")
            val twt: String?,
            @SerializedName("web")
            val web: String?,
        ) : Parcelable

        companion object : Parceler<CoinsAboutDataItem> {

            override fun CoinsAboutDataItem.write(parcel: Parcel, flags: Int) {
                parcel.writeString(currencyName)
                parcel.writeParcelable(info, flags)
            }

            override fun create(parcel: Parcel): CoinsAboutDataItem {
                return CoinsAboutDataItem(parcel)
            }
        }
    }
}