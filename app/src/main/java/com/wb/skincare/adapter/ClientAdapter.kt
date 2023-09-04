package com.wb.skincare.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wb.skincare.databinding.ConcactListItemBinding
import com.wb.skincare.models.ClientResponse

class ClientAdapter(): ListAdapter<ClientResponse.ClientData.Data, ClientAdapter.ClientsViewHolder>(ComparatorDiffUtil()) {

    class ClientsViewHolder(private val binding: ConcactListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(client: ClientResponse.ClientData.Data){
            binding.customerNameTVID.text=client.clientName
            Log.d("TAG", "bind: ${client.clientName}")
            //binding.tvShopName.text=allLead.organization
            //binding.serialNoId.text = (position+1).toString()
            binding.customerIdTVID.text= client.clientMobile

            /* val date = CommonMethods.dateFormatter(allLead.createdAt!!)
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


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientsViewHolder {
        val binding= ConcactListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ClientsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ClientsViewHolder, position: Int) {
        val allClients= getItem(position)
        allClients?.let {
            holder.bind(it)
        }
    }

    class ComparatorDiffUtil : DiffUtil.ItemCallback<ClientResponse.ClientData.Data>() {
        override fun areItemsTheSame(oldItem: ClientResponse.ClientData.Data, newItem: ClientResponse.ClientData.Data): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: ClientResponse.ClientData.Data, newItem: ClientResponse.ClientData.Data): Boolean {
            return oldItem==newItem
        }

    }
}