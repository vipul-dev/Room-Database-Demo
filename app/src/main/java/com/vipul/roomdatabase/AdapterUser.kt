package com.vipul.roomdatabase

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.vipul.roomdatabase.database.entities.User
import com.vipul.roomdatabase.databinding.ItemProfileBinding

class AdapterUser : Adapter<AdapterUser.UserViewHolder>() {

    private var mDataList: List<User> = emptyList()

    inner class UserViewHolder(itemProfileBinding: ItemProfileBinding) :
        ViewHolder(itemProfileBinding.root) {
        val binding = itemProfileBinding


        fun bind(user: User) {
            binding.name.text = user.name
            binding.mobileNumber.text = "+91 ${user.mobileNumber}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserViewHolder(
        ItemProfileBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun getItemCount() = mDataList.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(mDataList[position])
    }

    fun setData(mData: List<User>) {
        this.mDataList = mData
        notifyDataSetChanged()
    }
}