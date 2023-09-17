package com.wb.skincare.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wb.skincare.R
import com.wb.skincare.databinding.ContactListItemBinding
import com.wb.skincare.models.ClientResponse
import kotlin.reflect.KFunction1

class ClientAdapter(private val onClientItemClicked: KFunction1<ClientResponse.ClientData.Data, Unit>) : ListAdapter<ClientResponse.ClientData.Data, ClientAdapter.ClientsViewHolder>(ComparatorDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientsViewHolder {
        val binding= ContactListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ClientsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ClientsViewHolder, position: Int) {
        val allClients= getItem(position)
        allClients?.let {
            holder.bind(it)
        }
    }
    inner class ClientsViewHolder(private val binding: ContactListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(client: ClientResponse.ClientData.Data){
            binding.customerNameTVID.text=client.clientName
            Log.d("TAG", "bind: ${client.clientName}")
            //binding.tvShopName.text=allLead.organization
            //binding.serialNoId.text = (position+1).toString()
            binding.customerIdTVID.text= client.clientMobile

            /*binding.clientPhoneIconId.setOnClickListener {
                onClientItemClicked(client)
            }*/
            binding.btExpand.setOnClickListener {
                val popup = PopupMenu(it.getContext(), binding.btExpand)
                popup.menuInflater.inflate(R.menu.leads_menu, popup.menu)
                popup.setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.editId -> {
                            onClientItemClicked(client)
                            /*val item = list[position]
                            val id: Int = item.id!!
                            val projectId: Int = item.projectId!!
                            val user: Int = item.userId!!
                            val image = item.image
                            val shopName = item.organization
                            val projectName = item.projectData!!.projectName
                            val districtName = item.districtData!!.name
                            val districtId:Int = item.districtData!!.id!!
                            val email = item.email
                            val installCharge = item.installationCharge
                            val monthlyCharge = item.monthlyCharge
                            val interest = item.interest
                            val mobile1 = item.mobile1
                            val mobile2 = item.mobile2
                            val shopAddress1 = item.address1
                            val shopAddress2 = item.address2
                            CompletedContinuation.context.startActivity(
                                Intent(
                                    CompletedContinuation.context,
                                    UpdateShopActivity::class.java
                                )
                                    .putExtra("lead_id", id)
                                    .putExtra("user", user)
                                    .putExtra("image", image)
                                    .putExtra("shopName", shopName)
                                    .putExtra("projectName", projectName)
                                    .putExtra("districtName", districtName)
                                    .putExtra("districtId", districtId)
                                    .putExtra("email", email.toString())
                                    .putExtra("installCharge", installCharge)
                                    .putExtra("monthlyCharge", monthlyCharge)
                                    .putExtra("interest", interest)
                                    .putExtra("mobile1", mobile1)
                                    .putExtra("mobile2", mobile2)
                                    .putExtra("shopAddress1", shopAddress1)
                                    .putExtra("shopAddress2", shopAddress2.toString())
                                    .putExtra("projectId", projectId)
                            )*/
                        }

                        R.id.viewId -> {

                            //onClientItemClicked(client)
                            /*var installCharge = ""
                            var monthlyCharge = ""
                            var interest = ""

                            val item = list[position]
                            val userId:Int = item.userId!!
                            val leadId:Int = item.id!!
                            val image:String = item.image.toString()
                            val shopName:String = item.organization!!
                            val projectName:String = item.projectData!!.projectName!!
                            val email:String = item.email.toString()
                            if(item.installationCharge!=null){
                                installCharge  = item.installationCharge!!
                            }
                            if(item.monthlyCharge!=null){
                                monthlyCharge  = item.monthlyCharge!!
                            }
                            if(item.interest!=null){
                                interest = item.interest!!
                            }


                            val mobile1:String = item.mobile1!!
                            val mobile2:String = item.mobile2.toString()
                            val shopAddress1:String = item.address1!!
                            val shopAddress2:String = item.address2.toString()
                            val districtName:String = item.districtData!!.name!!
                            val distId:Int = item.districtId!!
                            val projectId:Int = item.projectId!!

                            click.onClickItem(
                                leadId,
                                image,
                                shopName,
                                projectName,
                                email ,
                                installCharge ,
                                monthlyCharge ,
                                interest,
                                mobile1,
                                mobile2,
                                shopAddress1,
                                shopAddress2,
                                userId,
                                districtName,
                                distId,
                                projectId
                            )*/

                        }

                    }
                    false
                }
                //displaying the popup
                popup.show()
            }


           /* binding.clientPhoneIconId.setOnClickListener {
                val dialIntent = Intent(Intent.ACTION_DIAL)
                dialIntent.data = Uri.parse("tel:" + client.clientMobile)
                context.startActivity(dialIntent)
            }*/

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

    class ComparatorDiffUtil : DiffUtil.ItemCallback<ClientResponse.ClientData.Data>() {
        override fun areItemsTheSame(oldItem: ClientResponse.ClientData.Data, newItem: ClientResponse.ClientData.Data): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: ClientResponse.ClientData.Data, newItem: ClientResponse.ClientData.Data): Boolean {
            return oldItem==newItem
        }

    }
}