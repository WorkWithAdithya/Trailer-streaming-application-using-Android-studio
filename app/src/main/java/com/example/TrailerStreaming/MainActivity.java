package com.example.TrailerStreaming;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.TrailerStreaming.adapter.BannerMoviesPagerAdapter;
import com.example.TrailerStreaming.adapter.MainRecyclerAdapter;
import com.example.TrailerStreaming.model.AllCategory;
import com.example.TrailerStreaming.model.BannerMovies;
import com.example.TrailerStreaming.model.CategoryItem;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

//import com.example.primemovieclone.retrofit.RetrofitClient;
//import com.example.primemovieclone.retrofit.RetrofitClient;

//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.disposables.CompositeDisposable;
//import io.reactivex.disposables.Disposable;
//import io.reactivex.observers.DisposableObserver;
//import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    BannerMoviesPagerAdapter bannerMoviesPagerAdapter;
    TabLayout indicatorTab, categoryTab;
    ViewPager bannerMoviesViewPager;

    List<BannerMovies> homeBannerList;
    List<BannerMovies> tvShowBannerList;
    List<BannerMovies> movieBannerList;
    List<BannerMovies> kidsBannerList;
    Timer sliderTimer;
    NestedScrollView nestedScrollView;
    AppBarLayout appBarLayout;


    MainRecyclerAdapter mainRecyclerAdapter;
    RecyclerView mainRecycler;
    List<AllCategory>allCategoryList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        indicatorTab = findViewById(R.id.tab_indicator);
        categoryTab = findViewById(R.id.tabLayout);
        nestedScrollView = findViewById(R.id.nested_scroll);
        appBarLayout = findViewById(R.id.appbar);




        homeBannerList = new ArrayList<>();
        homeBannerList.add(new BannerMovies(1, "EXTRACTION 2", "https://www.pinkvilla.com/images/2023-06/1649626176_.jpg", "mO0OuR26IZM"));
        homeBannerList.add(new BannerMovies(2, "BLEACH", "https://inasianspaces.files.wordpress.com/2022/10/bleach-tybw-kurosaki-ichigo.png?w=1200", "eA8-t-iyAV4"));
        homeBannerList.add(new BannerMovies(3, "MEAN GIRLS", "https://www.thehobbycenter.org/wp-content/uploads/2021/12/showWide-meanGirls-scaled.jpg", "oDU84nmSDZY"));
        homeBannerList.add(new BannerMovies(4, "TOP GUN", "https://i.ytimg.com/vi/giXco2jaZ_4/maxresdefault.jpg", "3ZFVEuAD-JY"));

        tvShowBannerList = new ArrayList<>();
        tvShowBannerList.add(new BannerMovies(1, "MONEY HEIST", "https://english.cdn.zeenews.com/sites/default/files/2021/05/25/938590-money-heist.png", "_InqQJRqGW4"));
        tvShowBannerList.add(new BannerMovies(2, "NEVER HAVE I EVER", "https://flxt.tmsimg.com/assets/p18130842_b_h8_aa.jpg", "HyOCCCbxwMQ"));
        tvShowBannerList.add(new BannerMovies(3, "BUSINESS PROPOSAL", "https://wallpapers.com/images/hd/business-proposal-min-kyu-and-in-ah-b019damkiuzowa6f.jpg", "M-PHcxPyasA"));
        tvShowBannerList.add(new BannerMovies(4, "STRANGER THINGS", "https://images.hindustantimes.com/rf/image_size_800x600/HT/p2/2016/07/11/Pictures/_afa6f3e2-471c-11e6-90e0-482a513bad8b.png", "sBEvEcpnG7k"));


        movieBannerList = new ArrayList<>();
        movieBannerList.add(new BannerMovies(1, "INTERSTELLAR", "https://i.ytimg.com/vi/YF1eYbfbH5k/maxresdefault.jpg?sqp=-oaymwEmCIAKENAF8quKqQMa8AEB-AH-CYAC0AWKAgwIABABGE4gYShlMA8=&rs=AOn4CLDt6JPoesvmQnP8qf-00JpeDZUfyA", "Lm8p5rlrSkY"));
        movieBannerList.add(new BannerMovies(2, "FROM UP ON THE POPY HILL", "https://payload.cargocollective.com/1/1/32536/12009997/poppynew1_800.jpg", "9nzpk_Br6yo"));
        movieBannerList.add(new BannerMovies(3, "bHOOL BHULAIYA 2", "https://resize.indiatvnews.com/en/resize/newbucket/1200_-/2022/05/bhool-bhulaiyaa-2-1652959038.jpg", "P2KRKxAb2ek"));
        movieBannerList.add(new BannerMovies(4, "END GAME", "https://i.ytimg.com/vi/79uhQ85n0YU/maxresdefault.jpg", "https://www.youtube.com/watch?v=TcMBFSGVi1c&t=6s"));

        kidsBannerList = new ArrayList<>();
        kidsBannerList.add(new BannerMovies(1, "OGGY AND COCKROACHES", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQqfdLxKokhRhMMzd7_r6norSgnFweJ4v4CA&usqp=CAU", "VsVimm8SMiM"));
        kidsBannerList.add(new BannerMovies(2, "MR BEAN", "https://i.ytimg.com/vi/-hG4e11i1Yk/maxresdefault.jpg", "Ipt1CwMGxhE"));
        kidsBannerList.add(new BannerMovies(3, "MR OSWALD", "https://v3img.voot.com/v3Storage/assets/oswald-1663305050806.jpg", "zKkDQiV0jFo"));
        kidsBannerList.add(new BannerMovies(4, "COURAGE THE COWARDLY DOG", "https://auralcrave.com/wp-content/uploads/2022/11/courage.jpg", "mxIH-kjL918"));

        setBannerMoviesPagerAdapter(homeBannerList);

        categoryTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 1:
                        setScrollDefaultState();
                        setBannerMoviesPagerAdapter(tvShowBannerList);
                        return;
                    case 2:
                        setScrollDefaultState();
                        setBannerMoviesPagerAdapter(movieBannerList);
                        return;
                    case 3:
                            setScrollDefaultState();
                        setBannerMoviesPagerAdapter(kidsBannerList);
                        return;
                    default:
                        setScrollDefaultState();
                        setBannerMoviesPagerAdapter(homeBannerList);
                        return;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        List<CategoryItem>homeCatListItem1= new ArrayList<>();
        homeCatListItem1.add(new CategoryItem(1,"EXTRACTION 2","https://www.pinkvilla.com/images/2023-06/1649626176_.jpg","mO0OuR26IZM"));
        homeCatListItem1.add(new CategoryItem(2,"BLEACH","https://inasianspaces.files.wordpress.com/2022/10/bleach-tybw-kurosaki-ichigo.png?w=1200","eA8-t-iyAV4"));
        homeCatListItem1.add(new CategoryItem(3,"MEAN GIRLS","https://www.thehobbycenter.org/wp-content/uploads/2021/12/showWide-meanGirls-scaled.jpg","oDU84nmSDZY"));
        homeCatListItem1.add(new CategoryItem(4,"TOP GUN","https://i.ytimg.com/vi/giXco2jaZ_4/maxresdefault.jpg","3ZFVEuAD-JY"));
        homeCatListItem1.add(new CategoryItem(4,"SIX BULLETS","https://images-na.ssl-images-amazon.com/images/S/pv-target-images/263df2496df2dba6439388ffb29dadb49ae9acdcc5bf150a4486e3e55463dc3b._RI_TTW_.jpg","N3pIv8JnBy8"));


        List<CategoryItem>homeCatListItem2= new ArrayList<>();
        homeCatListItem2.add(new CategoryItem(1,"MONEY HEIST","https://english.cdn.zeenews.com/sites/default/files/2021/05/25/938590-money-heist.png","SarjxS9nxvA"));
        homeCatListItem2.add(new CategoryItem(2,"NEVER HAVE I EVER","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQj8nIcC8mYmET7K7sbc1zl5IQQSBITgo7oEA&usqp=CAU","HyOCCCbxwMQ"));
        homeCatListItem2.add(new CategoryItem(3,"BUSINESS PROPOSAL","https://wallpapers.com/images/featured/business-proposal-qnl9r3783oto4w0p.jpg","M-PHcxPyasA"));
        homeCatListItem2.add(new CategoryItem(4,"STRANGER THINGS","https://wallpapers.com/images/featured/stranger-things-dkttxahzpl44tbsa.jpg","b9EkMc79ZSU"));
        homeCatListItem2.add(new CategoryItem(5,"DEMON SLAYER","https://img.etimg.com/thumb/width-640,height-480,imgsize-81192,resizemode-75,msid-99721158/news/new-updates/demon-slayer-season-3-episode-4-release-date-time-where-to-watch-and-more/demonslayer.jpg","VQGCKyvzIM4"));

        List<CategoryItem>homeCatListItem3= new ArrayList<>();

        homeCatListItem3.add(new CategoryItem(1,"VENOM","https://wallpapers.com/images/featured/venom-let-there-be-carnage-lwhfm22jp1hy9107.jpg","u9Mv98Gr5pY"));
        homeCatListItem3.add(new CategoryItem(2,"BAT-MAN","https://wallpapercave.com/wp/Gn6c2Wp.jpg","mqqft2x_Aa4"));
        homeCatListItem3.add(new CategoryItem(3,"DOREMON: LITTLE STAR WARS","https://img2.chinadaily.com.cn/images/202305/29/64746a65a310b60580cdeb4c.jpeg","-XJAOLcSECE"));
        homeCatListItem3.add(new CategoryItem(4,"TOM AND JERRY","https://upload.wikimedia.org/wikipedia/en/thumb/b/b0/Tomandjerrytales.jpg/220px-Tomandjerrytales.jpg","t0Q2otsqC4I"));
        homeCatListItem3.add(new CategoryItem(5,"PETER RABBIT","https://upload.wikimedia.org/wikipedia/commons/4/41/PeterRabbit8.jpg","vonD_FNHaVI"));
        homeCatListItem3.add(new CategoryItem(6,"JUNGLE BOOK","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSkqwxR3sOLrJNxG90Lb1AVaeMKvasfoOaoDQ&usqp=CAU","XB-dGmnBRfQ"));
        homeCatListItem3.add(new CategoryItem(7,"MOTU PATLU","https://v3img.voot.com/v3Storage/assets/2143836-16x9-1687502546.jpg","XlD6cG9XmNU"));
        homeCatListItem3.add(new CategoryItem(8,"KRIS: ROLL NO 21","https://tvwish.com/img/program/430898.jpg","mkRYrsOLcto"));

        List<CategoryItem>homeCatListItem4= new ArrayList<>();
        homeCatListItem4.add(new CategoryItem(1,"SONU KI TITU KI SWEETY","https://images.indianexpress.com/2018/02/sonu-759.jpg","M2q64UowX9g"));
        homeCatListItem4.add(new CategoryItem(2,"KANTARA","https://m.economictimes.com/thumb/msid-95076760,width-1200,height-900,resizemode-4,imgsize-99296/kantara-also-became-hombales-most-viewed-film-in-karnataka-.jpg","8mrVmf239GU"));
        homeCatListItem4.add(new CategoryItem(3,"PATHAAN","https://assets-in.bmscdn.com/discovery-catalog/events/et00323848-mzxdyjplce-landscape.jpg","vqu4z34wENw"));
        homeCatListItem4.add(new CategoryItem(4,"KABHI KUSHI KABHI GHAM","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS870HihZoEEUWX8ihVVZHTDfSPjEvLZLDZKw&usqp=CAU","7uY1JbWZKPA"));
        homeCatListItem4.add(new CategoryItem(5,"DIL DHADAKNE DO","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTTKliCYX8bb-8yMp2bQAZC2Q64TlxIJGLyTQ&usqp=CAU","qfnJCv4_1Ts"));
        homeCatListItem4.add(new CategoryItem(6,"THE ROYAL TREATMENT","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ3xM3dv4eQmOcU6uFaYUN22AZduC7WOCK0xQ&usqp=CAU","KWxJXZ3S3-g"));
        homeCatListItem4.add(new CategoryItem(7,"NEVER HAVE I EVER","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQj8nIcC8mYmET7K7sbc1zl5IQQSBITgo7oEA&usqp=CAU","HyOCCCbxwMQ"));
        homeCatListItem4.add(new CategoryItem(8,"ZINDAGI NA MILEGI DOOBARA","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQizFIFRhnzcNgHzOwZ4NxKeCOW8X98R9Chdg&usqp=CAU","ifIBOKCfjVs"));

        List<CategoryItem>homeCatListItem5= new ArrayList<>();
        homeCatListItem5.add(new CategoryItem(1,"SPY X FAMILY","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQZX9Z_qXiNc4I_CZRY9BwgLaiq8Euz_Zfwbw&usqp=CAU","CCXLUQzuigw"));
        homeCatListItem5.add(new CategoryItem(2,"BLUELOCK","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQOGCTIQeIkdH3boXeCHHJi34HLzO14e7-GKQ&usqp=CAU","Auw1Qy5k5nM"));
        homeCatListItem5.add(new CategoryItem(3,"NARUTO","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQXp0_bP4jwr9CFk5Dj4OLUyHqL3fs2f2M4vg&usqp=CAU","-G9BqkgZXRA"));
        homeCatListItem5.add(new CategoryItem(4,"TOKYO GHOUL","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSNSUUnCV2E8hI9hBGp49-GLpltbwkwU-JAFA&usqp=CAU","7aMOurgDB-o"));
        homeCatListItem2.add(new CategoryItem(4,"STRANGER THINGS","https://wallpapers.com/images/featured/stranger-things-dkttxahzpl44tbsa.jpg","b9EkMc79ZSU"));

        List<CategoryItem>homeCatListItem6= new ArrayList<>();
        homeCatListItem6.add(new CategoryItem(1,"EVIL DEAD","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfuu4gdvPYkIP_S2z71vtVSnWuoBDfTM_epg&usqp=CAU","BqQNO7BzN08"));
        homeCatListItem6.add(new CategoryItem(2,"CONJURING","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQztYRTjPw2YZF82PnJhLY9qdKAQy43tuys9w&usqp=CAU","ejMMn0t58Lc"));
        homeCatListItem6.add(new CategoryItem(3,"INCIDIOUS","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQGO6J9F3nMXnpj_fc6_SFh6VPQKbPUyN90Fw&usqp=CAU","gexw4P68kbg"));
        homeCatListItem6.add(new CategoryItem(4,"BHOOL BHULIYYA","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQTZH3njSa_if4CVFnhEXtlUY035cPDUbH0Iw&usqp=CAU","P2KRKxAb2ek"));
        homeCatListItem6.add(new CategoryItem(5,"OUIJA","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSh2cZCsnUKcVAiNZwR5q6HVlJ4H5W2slnEUA&usqp=CAU","MJIcZGEjjwo"));

        List<CategoryItem>homeCatListItem7= new ArrayList<>();
        homeCatListItem7.add(new CategoryItem(1,"THE VAMPIRE DAIRIES","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRL4dSVgnh0niXV-sZBYf6-TwPEbEAKHWcNNCl4Ey1tc5FFP2r9muTYSGYnPTlEQ3_HZ9w&usqp=CAU","BmVmhjjkN4E"));
        homeCatListItem7.add(new CategoryItem(2,"FRIENDS","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ8suqNzBOcV6db1c3RIr-c4ITJBOZ-gTnD0w&usqp=CAU","0mgZhgeKZ54"));
        homeCatListItem7.add(new CategoryItem(3,"TWILIGHT","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQIVOvDLTnQOCdlxehu2O44MYrFLOJql38s7A&usqp=CAU","QDRLSqm_WVg"));
        homeCatListItem7.add(new CategoryItem(4,"THE OFFICE","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT1BGlHPozazI9x9TOztFvC52GbRGRAQgkowg&usqp=CAU","tNcDHWpselE"));
        homeCatListItem7.add(new CategoryItem(5,"WEDNESDAY","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTBVNEdZUsDmgJnA4rIG3NKPbbOTfZcPQalMw&usqp=CAU","Di310WS8zLk"));


        allCategoryList = new ArrayList<>();
        allCategoryList.add(new AllCategory( 1, "WATCH NEXT TV SHOWS AND MOVIES  ",homeCatListItem1));
        allCategoryList.add(new AllCategory( 2, "BEST STREAMING",homeCatListItem2));
        allCategoryList.add(new AllCategory( 3, "KIDS AND FUN ",homeCatListItem3));
        allCategoryList.add(new AllCategory( 4, "FAMILY DRAMA",homeCatListItem4));
        allCategoryList.add(new AllCategory( 5, "ANIME",homeCatListItem5));
        allCategoryList.add(new AllCategory( 6, "THRILLER AND HORROR",homeCatListItem6));
        allCategoryList.add(new AllCategory( 7, "TEEN BINGE WATCH",homeCatListItem7));
        setMainRecycler(allCategoryList);




    }

    private void setBannerMoviesPagerAdapter(List<BannerMovies> bannerMoviesList) {
        bannerMoviesViewPager = findViewById(R.id.banner_viewPager);
        bannerMoviesPagerAdapter = new BannerMoviesPagerAdapter(this, bannerMoviesList);
        bannerMoviesViewPager.setAdapter(bannerMoviesPagerAdapter);
        indicatorTab.setupWithViewPager(bannerMoviesViewPager);
        Timer sliderTimer = new Timer();
        sliderTimer.scheduleAtFixedRate(new AutoSlider(), 4000, 6000);
        indicatorTab.setupWithViewPager(bannerMoviesViewPager, true);


    }

    class AutoSlider extends TimerTask {
        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (bannerMoviesViewPager.getCurrentItem() < homeBannerList.size() - 1) {
                        bannerMoviesViewPager.setCurrentItem(bannerMoviesViewPager.getCurrentItem() + 1);
                    } else {
                        bannerMoviesViewPager.setCurrentItem(0);
                    }
                }
            });
        }

    }


    public void setMainRecycler(List<AllCategory> allCategoryList) {
        mainRecycler = findViewById(R.id.main_recycler);

                RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
                mainRecycler.setLayoutManager(layoutManager);
                mainRecyclerAdapter= new MainRecyclerAdapter(this,allCategoryList);
                mainRecycler.setAdapter(mainRecyclerAdapter);
    }
    private void setScrollDefaultState()
    {
        nestedScrollView.fullScroll(View.FOCUS_UP);
        nestedScrollView.scrollTo(0,0);
        appBarLayout.setExpanded(true);
    }






    }




