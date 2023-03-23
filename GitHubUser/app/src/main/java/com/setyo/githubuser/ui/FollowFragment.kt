package com.setyo.githubuser.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.setyo.githubuser.adapter.ListUsersAdapter
import com.setyo.githubuser.databinding.FragmentFollowBinding
import com.setyo.githubuser.viewmodel.FollowViewModel

class FollowFragment : Fragment() {

    private var _binding: FragmentFollowBinding? = null
    private val binding get() = _binding!!
    private val followViewModel by viewModels<FollowViewModel>()

    private var position: Int = 0
    private var username: String? = "test"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            position = it.getInt(ARG_POSITION)
            username = it.getString(ARG_USERNAME)
        }


        binding.rvFollowerFollowing.adapter = ListUsersAdapter(emptyList())
        showRecyclerView()

        followViewModel.listUserFollower.observe(viewLifecycleOwner) { listFollower ->
            binding.rvFollowerFollowing.adapter = ListUsersAdapter(listFollower)
        }

        followViewModel.listUserFollowing.observe(viewLifecycleOwner) {listFollowing ->
            binding.rvFollowerFollowing.adapter =ListUsersAdapter(listFollowing)
        }

        followViewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }

        followViewModel.textToast.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { textToast ->
                Toast.makeText(
                    requireContext(), textToast, Toast.LENGTH_SHORT
                ).show()
            }
        }

        if (position == 1) {
           followViewModel.followerUser(username)
        } else {
            followViewModel.followingUser(username)
        }
    }

    private fun showRecyclerView() {
        binding.rvFollowerFollowing.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    companion object {
        const val ARG_POSITION = "arg_position"
        const val ARG_USERNAME = "arg_username"
    }
}