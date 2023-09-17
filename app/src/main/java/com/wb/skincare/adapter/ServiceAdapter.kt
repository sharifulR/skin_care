package com.wb.skincare.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wb.skincare.databinding.CategoryItemListBinding
import com.wb.skincare.models.ServiceResponse
import kotlin.reflect.KFunction1

class ServiceAdapter(private val onServiceItemClicked: KFunction1<ServiceResponse.ServiceData.Data, Unit>) : ListAdapter<ServiceResponse.ServiceData.Data, ServiceAdapter.ServicesViewHolder>(ComparatorDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicesViewHolder {
        val binding= CategoryItemListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ServicesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ServicesViewHolder, position: Int) {
        val allServices= getItem(position)
        allServices?.let {
            holder.bind(it)
        }
    }
    inner class ServicesViewHolder(private val binding: CategoryItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(service: ServiceResponse.ServiceData.Data){
            binding.serviceName.text=service.serviceName
            Log.d("TAG", "bind: ${service.serviceName}")
            binding.servicePrice.text= service.servicePrice.toString()

            binding.root.setOnClickListener {
                onServiceItemClicked(service)
            }

            /* val date = CommonMethods.dateFormatter(allServices.createdAt!!)
             binding.dateId.text = date
             val image = allLead.image
 //            if (image == null) {
 //                binding.image.background =
 //                    context.resources.getDrawable(R.drawable.ic_shop_image)
 //            }else{

             Glide.with(context)
                 .load(Constants.BASE_URL + "uploads/organization_image/$image")
                 .diskCacheStrategy(DiskCacheStrategy.ALL)

                 .apply(
                     RequestOptions()
                         .error(R.drawable.ic_shop_image)
                 )
                 .into(binding.image)*/
        }

    }

    class ComparatorDiffUtil : DiffUtil.ItemCallback<ServiceResponse.ServiceData.Data>() {
        override fun areItemsTheSame(oldItem: ServiceResponse.ServiceData.Data, newItem: ServiceResponse.ServiceData.Data): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: ServiceResponse.ServiceData.Data, newItem: ServiceResponse.ServiceData.Data): Boolean {
            return oldItem==newItem
        }

    }
}