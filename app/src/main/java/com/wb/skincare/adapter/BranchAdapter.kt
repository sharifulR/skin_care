package com.wb.skincare.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wb.skincare.databinding.BranchListItemBinding
import com.wb.skincare.models.branch.BranchResponse
import kotlin.reflect.KFunction1

class BranchAdapter (private val onProviderItemClicked: KFunction1<BranchResponse.BranchData.Data, Unit>) : ListAdapter<BranchResponse.BranchData.Data, BranchAdapter.ProvidersViewHolder>(ComparatorDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProvidersViewHolder {
        val binding= BranchListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProvidersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProvidersViewHolder, position: Int) {
        val allProviders= getItem(position)
        allProviders?.let {
            holder.bind(it)
        }
    }
    inner class ProvidersViewHolder(private val binding: BranchListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(branch: BranchResponse.BranchData.Data){
            binding.branchNameTVID.text=branch.branchTitle
            binding.branchAddressTVID.text=branch.branchAddress
            Log.d("TAG", "bind: ${branch.branchTitle}")


            /*binding.btExpand.setOnClickListener {
                val popup = PopupMenu(it.getContext(), binding.btExpand)
                popup.menuInflater.inflate(R.menu.leads_menu, popup.menu)
                popup.setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.editId -> {
                            onProviderItemClicked(providers)
                            *//*val item = list[position]
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
                            )*//*
                        }

                        R.id.viewId -> {

                            //onClientItemClicked(client)
                            *//*var installCharge = ""
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
                            )*//*

                        }

                    }
                    false
                }
                //displaying the popup
                popup.show()
            }*/

        }

    }

    class ComparatorDiffUtil : DiffUtil.ItemCallback<BranchResponse.BranchData.Data>() {
        override fun areItemsTheSame(oldItem: BranchResponse.BranchData.Data, newItem: BranchResponse.BranchData.Data): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: BranchResponse.BranchData.Data, newItem: BranchResponse.BranchData.Data): Boolean {
            return oldItem==newItem
        }

    }
}