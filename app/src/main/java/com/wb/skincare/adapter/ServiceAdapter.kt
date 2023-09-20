package com.wb.skincare.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wb.skincare.databinding.CategoryItemListBinding
import com.wb.skincare.databinding.ServiceListItemBinding
import com.wb.skincare.models.service.CategoryWiseService
import java.security.Provider.Service
import kotlin.reflect.KFunction1

class ServiceAdapter(private val onServiceItemClicked: KFunction1<CategoryWiseService.ServiceData, Unit>) : ListAdapter<CategoryWiseService.ServiceData, ServiceAdapter.ServicesViewHolder>(ComparatorDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicesViewHolder {
        val binding= ServiceListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ServicesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ServicesViewHolder, position: Int) {
        val allServices= getItem(position)
        allServices?.let {
            holder.bind(it)
        }
    }
    inner class ServicesViewHolder(private val binding: ServiceListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(service: CategoryWiseService.ServiceData){
            binding.serviceName.text=service.serviceName
            Log.d("TAG", "bind: ${service.serviceName}")
            binding.servicePrice.text= service.servicePrice.toString()+"/-"

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

    class ComparatorDiffUtil : DiffUtil.ItemCallback<CategoryWiseService.ServiceData>() {
        override fun areItemsTheSame(oldItem: CategoryWiseService.ServiceData, newItem: CategoryWiseService.ServiceData): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: CategoryWiseService.ServiceData, newItem: CategoryWiseService.ServiceData): Boolean {
            return oldItem==newItem
        }

    }
}