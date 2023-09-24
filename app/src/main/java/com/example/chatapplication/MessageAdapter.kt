package com.example.chatapplication

import android.content.Context
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.firebase.auth.FirebaseAuth


private val Message.message: CharSequence?
    get() {
        TODO("Not yet implemented")
    }
private val Message.senderId: String?
    get() {
        TODO("Not yet implemented")
    }

class MessageAdapter(val context: Context, val messageList: ArrayList<com.example.chatapplication.Message>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    val ITEM_RECEIVE = 1;
    val ITEM_SENT = 2;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        if(viewType ==1 ){
            // inflate recieve
            val view: View = LayoutInflater.from(context).inflate(R.layout.recieve, parent,false)
            return ReceiveViewHolder(view)
        }
        else{
            // inflate sent
            val view: View = LayoutInflater.from(context).inflate(R.layout.sent, parent,false)
            return sentViewHolder(view)
        }
    }



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentMessage = messageList[position]
        if(holder.javaClass== sentViewHolder::class.java){
            // do the stuff for sent view holder


            val viewHolder = holder as sentViewHolder
            holder.sentMessage.text =currentMessage.message
        }
        else{
            // do the stuff for recieve view holder
            val viewHolder = holder as ReceiveViewHolder
            holder.receiveMessage.text = currentMessage.message
        }
    }



    override fun getItemViewType(position: Int): Int {
        val currentMessage = messageList[position]
        if(FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.senderId)){
            return ITEM_SENT
        }
        else{
            return ITEM_RECEIVE
        }
    }
    override fun getItemCount(): Int {
        return messageList.size
    }
    class sentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val sentMessage = itemView.findViewById<TextView>(R.id.txt_sent_message)
    }

    class ReceiveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val receiveMessage= itemView.findViewById<TextView>(R.id.txt_receive_message)
    }
}