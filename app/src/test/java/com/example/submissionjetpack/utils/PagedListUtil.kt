package com.example.submissionjetpack.utils

import androidx.paging.PagedList
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

object PagedListUtil {
    fun <T> mockPagedList(list: List<T>): PagedList<*>{
        val pagedList = mock(PagedList::class.java) as PagedList<*>
        `when`(pagedList[anyInt()]).then {
            val index = it.arguments.first() as Int
            list[index]
        }
        `when`(pagedList.size).thenReturn(list.size)

        return pagedList
    }
}