package com.johnpinilla.androidmaster

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.johnpinilla.androidmaster.blaaier.BlaaierActivity
import com.johnpinilla.androidmaster.noti.viewsNotiActivity
import com.johnpinilla.androidmaster.searchsuperheroe.SuperHeroListActivity
class MenuActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var videoView: VideoView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

       /* val btnVideo = findViewById<Button>(R.id.btnVideo)
        btnVideo.setOnClickListener { viewVideo() }*/


        val videoView = findViewById<VideoView>(R.id.xml_video_view)
        // Ruta del video local
        val videoUri = Uri.parse("android.resource://" + packageName + "/" + R.raw.video2)

        // Configurar el MediaController para los controles de reproduccion
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)

        // Establecer la fuente del video
        videoView.setVideoURI(videoUri)

        // Iniciar la reproduccion del video
        videoView.start()

        val toolbar: Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout)

        toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
             R.id.nav_item_one -> {
                 navigateToSaludApp()
             }
            R.id.nav_item_two -> {
                navigateSearchHeroe()
            }
            R.id.nav_item_three -> {
                navigateNotiView()
            }
            R.id.nav_item_four -> {
                navigateBlaaider()
            }

        }

        drawer.closeDrawer(GravityCompat.START)

        return true
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun navigateToSaludApp(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun navigateSearchHeroe() {
        val intent = Intent(this, SuperHeroListActivity::class.java)
        startActivity(intent)
    }

    private fun navigateNotiView(){
        val intent = Intent(this, viewsNotiActivity::class.java)
        startActivity(intent)
    }

    private fun navigateBlaaider(){
        val intent = Intent(this, BlaaierActivity::class.java)
        startActivity(intent)
    }

    /*private fun viewVideo() {

        val url = "https://www.youtube.com/watch?v=4f8rOsTeWJU&t=7s"
        val videoId = extractYouTubeVideoId(url)
        Log.i("johnpinilla",videoId)
        val youTubePlayerView = findViewById<YouTubePlayerView>(R.id.youtube_player_view)
        lifecycle.addObserver(youTubePlayerView)
        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer) {
                super.onReady(youTubePlayer)
                youTubePlayer.loadVideo(videoId, 0f)
                youTubePlayer.play()
            }
        })
    }*/

    // Función para extraer el ID del video de una URL de YouTube
    private fun extractYouTubeVideoId(youtubeUrl: String): String {
        val pattern = "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed\\?video_id=|&v=)([\\w-]+)($|&|\\?|#)".toRegex()
        val matchResult = pattern.find(youtubeUrl)
        return matchResult?.value ?: ""
    }

}