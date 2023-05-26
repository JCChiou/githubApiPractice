package com.example.githubsearchrepo.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubsearchrepo.data.remote.dto.Item
import com.example.githubsearchrepo.databinding.DefaultHeaderBinding
import com.example.githubsearchrepo.databinding.HeaderViewBinding
import com.example.githubsearchrepo.databinding.ItemViewBinding


enum class VT {
    Header,
    Content,
}

class SearchRepoAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var settingItemsList = listOf<Item>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root) {

        private val title = binding.tvTitle
        private val description = binding.textView2
        fun bindData(data: Item) {
            title.text = data.name
            description.text = data.description
        }
    }

    inner class HeaderViewHolder(binding: HeaderViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val headTitle = binding.textView
        private val headContent = binding.textView3
        fun bind() {
            headTitle.text = "Title"
            headContent.text = "description"
        }
    }

    inner class DefaultViewHolder(binding: DefaultHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d("TAG", "Create View Holder")
        return when (viewType) {
//            VT.Header.ordinal -> {
//                HeaderViewHolder(
//                    HeaderViewBinding.inflate(
//                        LayoutInflater.from(parent.context),
//                        parent,
//                        false
//                    )
//                )
//            }
            VT.Content.ordinal -> {
                ViewHolder(
                    ItemViewBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> {
                DefaultViewHolder(
                    DefaultHeaderBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }

    }


    override fun getItemCount(): Int {
        Log.d("TAG", "get Item count, ${settingItemsList.size}")
        return settingItemsList.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        Log.d("TAG", "get Item View")
        return if (position == 0) {
            VT.Header.ordinal
        } else {
            VT.Content.ordinal
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("TAG", " OnBind position = ${position}")
        when (holder.itemViewType) {
            VT.Header.ordinal -> {
//                (holder as HeaderViewHolder).bind()
            }
            VT.Content.ordinal -> {
                (holder as ViewHolder).bindData(settingItemsList[position - 1])
            }
        }
    }
}