package com.wb.skincare.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wb.skincare.databinding.CategoryItemListBinding
import com.wb.skincare.models.ServiceCategoryResponse
import kotlin.reflect.KFunction1

class ServiceCategoryAdapter (private val onServiceItemClicked: KFunction1<ServiceCategoryResponse.ServiceCategoryData.Data, Unit>) : ListAdapter<ServiceCategoryResponse.ServiceCategoryData.Data, ServiceCategoryAdapter.ServicesCategoryViewHolder>(ComparatorDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicesCategoryViewHolder {
        val binding= CategoryItemListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ServicesCategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ServicesCategoryViewHolder, position: Int) {
        val allServices= getItem(position)
        allServices?.let {
            holder.bind(it)
        }
    }
    inner class ServicesCategoryViewHolder(private val binding: CategoryItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(service: ServiceCategoryResponse.ServiceCategoryData.Data){
            binding.serviceName.text=service.categoryName
            Log.d("TAG", "bind: ${service.categoryName}")
            //binding.servicePrice.text= service.servicePrice.toString()

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

    class ComparatorDiffUtil : DiffUtil.ItemCallback<ServiceCategoryResponse.ServiceCategoryData.Data>() {
        override fun areItemsTheSame(oldItem: ServiceCategoryResponse.ServiceCategoryData.Data, newItem: ServiceCategoryResponse.ServiceCategoryData.Data): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: ServiceCategoryResponse.ServiceCategoryData.Data, newItem: ServiceCategoryResponse.ServiceCategoryData.Data): Boolean {
            return oldItem==newItem
        }

    }
}