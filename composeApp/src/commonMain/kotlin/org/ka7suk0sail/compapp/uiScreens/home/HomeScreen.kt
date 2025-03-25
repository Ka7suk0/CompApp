package org.ka7suk0sail.compapp.uiScreens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.lifecycle.viewmodel.compose.viewModel
import compapp.composeapp.generated.resources.Res
import compapp.composeapp.generated.resources.app_name
import compapp.composeapp.generated.resources.avatar
import compapp.composeapp.generated.resources.chat_notifications
import compapp.composeapp.generated.resources.compapp_logo
import compapp.composeapp.generated.resources.menu
import compapp.composeapp.generated.resources.my_tasks
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.ka7suk0sail.compapp.Models.Data.Favor
import org.ka7suk0sail.compapp.Themes.PrimaryBlack
import org.ka7suk0sail.compapp.uiScreens.Screen
import org.ka7suk0sail.compapp.uiScreens.home.FavorDetailsDialog
import org.ka7suk0sail.compapp.uiScreens.home.EducationDetailsDialog
import org.ka7suk0sail.compapp.uiScreens.home.RideDetailsDialog
import org.ka7suk0sail.compapp.Models.Data.Education
import org.ka7suk0sail.compapp.Models.Data.Ride
import org.ka7suk0sail.compapp.Themes.PrimaryYellow
import org.ka7suk0sail.compapp.Themes.ThirdDarkYellow
import org.ka7suk0sail.compapp.ViewModels.EducationDetailViewModel
import org.ka7suk0sail.compapp.ViewModels.FavorDetailViewModel
import org.ka7suk0sail.compapp.ViewModels.HomeViewModel
import org.ka7suk0sail.compapp.ViewModels.RideDetailViewModel


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    onFavorClick: (Favor) -> Unit,
    onEducationClick: (Education) -> Unit,
    onRideClick: (Ride) -> Unit,
    vm: HomeViewModel
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { 3 })

    var showFavorDialog by remember { mutableStateOf(false) }
    var showEducationDialog by remember { mutableStateOf(false) }
    var showRideDialog by remember { mutableStateOf(false) }

    // Keep these states mutable so they can be reassigned
    var selectedFavor by remember { mutableStateOf<Favor?>(null) }
    var selectedEducation by remember { mutableStateOf<Education?>(null) }
    var selectedRide by remember { mutableStateOf<Ride?>(null) }

    Screen {
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = { ModalDrawerSheet { DrawerMenu() } }
        ) {
            Scaffold(
                topBar = {
                    HomeTopAppBar(
                        drawerState = drawerState,
                        coroutineScope = coroutineScope,
                        scrollBehavior = scrollBehavior
                    )
                },
                floatingActionButton = {
                    HomeFloatingActionButton(pagerState) {
                        when (it) {
                            0 -> showFavorDialog = true
                            1 -> showEducationDialog = true
                            2 -> showRideDialog = true
                        }
                    }
                },
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
            ) { padding ->
                HomeContent(
                    pagerState = pagerState,
                    vm = vm,
                    onFavorClick = { favor ->
                        selectedFavor = favor
                    },
                    onEducationClick = { education ->
                        selectedEducation = education
                    },
                    onRideClick = { ride ->
                        selectedRide = ride
                    },
                    showFavorDialog = showFavorDialog,
                    showEducationDialog = showEducationDialog,
                    showRideDialog = showRideDialog,
                    onDismissDialogs = {
                        showFavorDialog = false
                        showEducationDialog = false
                        showRideDialog = false
                    },
                    padding = padding,
                    coroutineScope = coroutineScope,
                    selectedFavor = selectedFavor,
                    selectedEducation = selectedEducation,
                    selectedRide = selectedRide,
                    onDismissFavor = { selectedFavor = null },  // Mutable reassignment
                    onDismissEducation = { selectedEducation = null },  // Mutable reassignment
                    onDismissRide = { selectedRide = null }  // Mutable reassignment
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(
    drawerState: DrawerState,
    coroutineScope: CoroutineScope,
    scrollBehavior: TopAppBarScrollBehavior
) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                // Logo
                Image(
                    painter = painterResource(Res.drawable.compapp_logo),
                    contentDescription = null,
                    modifier = Modifier
                        .height(40.dp)
                        .width(150.dp)
                )
            }
        },
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            IconButton(onClick = { coroutineScope.launch { drawerState.open() } }) {
                Icon(
                    imageVector = Icons.Rounded.Menu,
                    contentDescription = stringResource(Res.string.menu),
                    tint = PrimaryBlack
                )
            }
        },
        actions = {
            IconButton(onClick = {/*TODO filters*/ }) {
                Icon(
                    imageVector = Icons.Rounded.Check,
                    contentDescription = stringResource(Res.string.my_tasks),
                    tint = PrimaryBlack
                )
            }
            IconButton(onClick = { /*TODO options*/ }) {
                Icon(
                    imageVector = Icons.Rounded.Notifications,
                    contentDescription = stringResource(Res.string.chat_notifications),
                    tint = PrimaryBlack
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = PrimaryYellow
        )
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeFloatingActionButton(
    pagerState: PagerState,
    onClick: (Int) -> Unit
) {
    FloatingActionButton(
        onClick = { onClick(pagerState.currentPage) },
        containerColor = PrimaryYellow,
        contentColor = PrimaryBlack
    ) {
        Icon(Icons.Default.Add, contentDescription = "Add Item")
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeContent(
    pagerState: PagerState,
    vm: HomeViewModel,
    onFavorClick: (Favor) -> Unit,
    onEducationClick: (Education) -> Unit,
    onRideClick: (Ride) -> Unit,
    showFavorDialog: Boolean,
    showEducationDialog: Boolean,
    showRideDialog: Boolean,
    onDismissDialogs: () -> Unit,
    padding: PaddingValues,
    coroutineScope: CoroutineScope,
    selectedFavor: Favor?,
    selectedEducation: Education?,
    selectedRide: Ride?,
    onDismissFavor: () -> Unit,  // Corrected callback for mutable state
    onDismissEducation: () -> Unit,  // Corrected callback for mutable state
    onDismissRide: () -> Unit  // Corrected callback for mutable state
) {
    Column(modifier = Modifier.padding(padding)) {
        val tabs = listOf("ENCARGOS", "EDUCACIÓN", "RIDES")

        TabRow(
            selectedTabIndex = pagerState.currentPage,
            containerColor = PrimaryYellow,
            contentColor = PrimaryBlack,
            indicator = { tabPositions ->
                val currentTabPosition = tabPositions[pagerState.currentPage]
                TabRowDefaults.SecondaryIndicator(
                    Modifier
                        .fillMaxWidth()
                        .wrapContentSize(align = Alignment.BottomStart)
                        .offset {
                            IntOffset(
                                x = currentTabPosition.left.toPx().toInt(),
                                y = 0
                            )
                        },
                    color = PrimaryBlack
                )
            }
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = {
                        Text(
                            text = title,
                            color = if (pagerState.currentPage == index) PrimaryBlack else ThirdDarkYellow
                        )
                    },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
                )
            }
        }

        HorizontalPager(state = pagerState) { page ->
            when (page) {
                0 -> FavorsScreen(
                    vm,
                    onFavorClick
                )
                1 -> EducationScreen(
                    vm,
                    onEducationClick
                )
                2 -> RidesScreen(
                    vm,
                    onRideClick
                )
            }
        }

        if (selectedFavor != null) {
            FavorDetailsDialog(
                vm = FavorDetailViewModel(selectedFavor.favorID, vm.favorsRepository),
                onDismiss = onDismissFavor  // Correct callback usage
            )
        }

        if (selectedEducation != null) {
            EducationDetailsDialog(
                vm = EducationDetailViewModel(selectedEducation.educationID, vm.educationsRepository),
                onDismiss = onDismissEducation  // Correct callback usage
            )
        }

        if (selectedRide != null) {
            RideDetailsDialog(
                vm = RideDetailViewModel(selectedRide.rideID, vm.ridesRepository),
                onDismiss = onDismissRide  // Correct callback usage
            )
        }

        if (showFavorDialog) {
            FavorDialog(onDismiss = onDismissDialogs, usuario = "Katsuko") /* TODO */
        }

        if (showEducationDialog) {
            EducationDialog(onDismiss = onDismissDialogs, usuario = "Katsuko") /* TODO */
        }

        if (showRideDialog) {
            RideDialog(onDismiss = onDismissDialogs, usuario = "Katsuko") /* TODO */
        }
    }
}
