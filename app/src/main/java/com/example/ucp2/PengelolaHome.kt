package com.example.ucp2

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.data.Dosen.dosen1

enum class PengelolaHome {
    Home,
    Formulir,
    Summary
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormApp(
    viewModel: UcpViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    Scaffold { inner ->
        val uiState by viewModel.stateUI.collectAsState()
        NavHost(
            navController = navController,
            startDestination = PengelolaHome.Home.name,
            modifier = Modifier.padding(inner)
        ) {
            composable(route = PengelolaHome.Home.name) {
                HalamanSatu(
                    onNextButtonClicked = {
                        navController.navigate(PengelolaHome.Formulir.name)
                    }
                )
            }
            composable(route = PengelolaHome.Formulir.name) {
                val context = LocalContext.current
                HalamanDua(
                    dosenpembimbing1 = dosen1.map { id -> context.resources.getString(id) },
                    dosenpembimbing2 = dosen1.map { id -> context.resources.getString(id) },
                    onSelectionChanged1 = {viewModel.setDosenPem1(it)},
                    onSelectionChanged2 = {viewModel.setDosenPem2(it)},
                    onSubmitClicked = {viewModel.setData(it)
                        navController.navigate(PengelolaHome.Formulir.name)
                    },
                )
            }
//            composable(route = PengelolaHome.Summary.name) {
//                HalamanTiga(FormUIState = uiState, onCancelButtonClicked = { navController.popBackStack(PengelolaHalaman.Formulir.name,inclusive = false) })
//            }
        }
    }
}