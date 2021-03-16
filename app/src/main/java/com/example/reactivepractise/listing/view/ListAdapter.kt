package com.example.reactivepractise.listing.view

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.reactivepractise.R
import com.example.reactivepractise.databinding.FooterItemLayoutBinding
import com.example.reactivepractise.databinding.ListItemLayoutBinding
import com.example.reactivepractise.listing.model.GithubCustomItem
import com.example.reactivepractise.utils.clickCallBack
import com.example.reactivepractise.utils.inflateLayout

class ListAdapter: PagedListAdapter<GithubCustomItem, ListAdapter.ViewHolder>(DiffCallback) {

//    val DATA_VIEW_TYPE=0
//    val FOOTER_VIEW_TYPE=1
    
    abstract class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        abstract fun bindView(item: GithubCustomItem?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ItemViewHolder(parent.inflateLayout(R.layout.fragment_list_item))
    }
    
    inner class ItemViewHolder(val binding: ListItemLayoutBinding): ViewHolder(binding.root){
        init {
            binding.ivAvatar.clickCallBack {
                Log.d("tag", "clicked")
            }
        }

        override fun bindView(item: GithubCustomItem?)= item?.let {mItem->
            binding.imageUrl = mItem.image
            binding.repoName = mItem.name
            binding.viewColor = Color.CYAN

            binding.executePendingBindings()
        } ?: kotlin.run {
            binding.unbind()
        }
    }
    
    inner class FooterViewHolder(val binding: FooterItemLayoutBinding): ViewHolder(binding.root){
        override fun bindView(item: GithubCustomItem?) {
        }
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<GithubCustomItem>() {
            override fun areItemsTheSame(oldItem: GithubCustomItem, newItem: GithubCustomItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: GithubCustomItem, newItem: GithubCustomItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}