package com.aidchow.doubanmovie.data

/**
 * Created by aidchow on 17-5-28.
 *
 * the movie info
 */

class MovieSubject {

    /**
     * rating : {"max":10,"average":8.3,"stars":"45","min":0}
     * reviews_count : 536
     * wish_count : 61389
     * douban_site :
     * year : 2013
     * images : {"small":"http://img7.doubanio.com/view/movie_poster_cover/ipst/public/p2187896711.jpg","large":"http://img7.doubanio.com/view/movie_poster_cover/lpst/public/p2187896711.jpg","medium":"http://img7.doubanio.com/view/movie_poster_cover/spst/public/p2187896711.jpg"}
     * alt : https://movie.douban.com/subject/25807345/
     * id : 25807345
     * mobile_url : https://movie.douban.com/subject/25807345/mobile
     * title : 彗星来的那一夜
     * do_count : null
     * share_url : http://m.douban.com/movie/subject/25807345
     * seasons_count : null
     * schedule_url :
     * episodes_count : null
     * countries : ["美国","英国"]
     * genres : ["科幻","悬疑","惊悚"]
     * collect_count : 168865
     * casts : [{"alt":"https://movie.douban.com/celebrity/1343010/","avatars":{"small":"http://img7.doubanio.com/img/celebrity/small/CictjBsUJs0cel_avatar_uploaded1410769575.65.jpg","large":"http://img7.doubanio.com/img/celebrity/large/CictjBsUJs0cel_avatar_uploaded1410769575.65.jpg","medium":"http://img7.doubanio.com/img/celebrity/medium/CictjBsUJs0cel_avatar_uploaded1410769575.65.jpg"},"name":"艾米丽·芭尔多尼","id":"1343010"},{"alt":"https://movie.douban.com/celebrity/1151824/","avatars":{"small":"http://img3.doubanio.com/img/celebrity/small/46289.jpg","large":"http://img3.doubanio.com/img/celebrity/large/46289.jpg","medium":"http://img3.doubanio.com/img/celebrity/medium/46289.jpg"},"name":"莫瑞·史特林","id":"1151824"},{"alt":"https://movie.douban.com/celebrity/1000085/","avatars":{"small":"http://img7.doubanio.com/img/celebrity/small/24072.jpg","large":"http://img7.doubanio.com/img/celebrity/large/24072.jpg","medium":"http://img7.doubanio.com/img/celebrity/medium/24072.jpg"},"name":"尼古拉斯·布兰登","id":"1000085"},{"alt":"https://movie.douban.com/celebrity/1056173/","avatars":{"small":"http://img7.doubanio.com/img/celebrity/small/1390315130.14.jpg","large":"http://img7.doubanio.com/img/celebrity/large/1390315130.14.jpg","medium":"http://img7.doubanio.com/img/celebrity/medium/1390315130.14.jpg"},"name":"伊丽莎白·格瑞斯","id":"1056173"}]
     * current_season : null
     * original_title : Coherence
     * summary : 在一个静得有些诡异阴森的夜晚，年轻女子艾米丽（艾米丽·芭尔多尼 Emily Baldoni 饰）驱车来到朋友家参加聚会。此前她一边开车一边和男友打电话，然而通话突然中断，她的手机屏也莫名其妙出现裂痕。这一事件让艾米丽的心中升起隐隐不安，因为当晚一颗彗星刚好接近地球，一个 很久之前听过的传说使她对彗星的到来有着不祥的预感。稍后，朋友们落座，大家彼此交谈，叙说近况，虽然言语中有着许多莫名其妙、蹊跷反常的线索，然而均被一带而过。突然间，灯光尽灭。当光明重新来临时，众人发现附近只有一户人家还在亮灯，而亮灯人家里坐着的竟是……
     * 本片荣获2014年阿姆斯特丹奇幻电影节黑郁金香奖。©豆瓣
     * subtype : movie
     * directors : [{"alt":"https://movie.douban.com/celebrity/1342405/","avatars":{"small":"http://img3.doubanio.com/img/celebrity/small/f4uD1Ah3aRscel_avatar_uploaded1408558838.98.jpg","large":"http://img3.doubanio.com/img/celebrity/large/f4uD1Ah3aRscel_avatar_uploaded1408558838.98.jpg","medium":"http://img3.doubanio.com/img/celebrity/medium/f4uD1Ah3aRscel_avatar_uploaded1408558838.98.jpg"},"name":"詹姆斯·沃德·布柯特","id":"1342405"}]
     * comments_count : 53557
     * ratings_count : 146421
     * aka : ["相干性","相干效应"]
     */

    var rating: RatingBean? = null
    var reviews_count: Int = 0
    var wish_count: Int = 0
    var douban_site: String? = null
    var year: String? = null
    var images: ImagesBean? = null
    var alt: String? = null
    var id: String? = null
    var mobile_url: String? = null
    var title: String? = null
    var do_count: Any? = null
    var share_url: String? = null
    var seasons_count: Any? = null
    var schedule_url: String? = null
    var episodes_count: Any? = null
    var collect_count: Int = 0
    var current_season: Any? = null
    var original_title: String? = null
    var summary: String? = null
    var subtype: String? = null
    var comments_count: Int = 0
    var ratings_count: Int = 0
    var countries: List<String>? = null
    var genres: List<String>? = null
    var casts: List<CastsBean>? = null
    var directors: List<DirectorsBean>? = null
    var aka: List<String>? = null

    class RatingBean {
        /**
         * max : 10
         * average : 8.3
         * stars : 45
         * min : 0
         */

        var max: Int = 0
        var average: Double = 0.toDouble()
        var stars: String? = null
        var min: Int = 0
    }

    class ImagesBean {
        /**
         * small : http://img7.doubanio.com/view/movie_poster_cover/ipst/public/p2187896711.jpg
         * large : http://img7.doubanio.com/view/movie_poster_cover/lpst/public/p2187896711.jpg
         * medium : http://img7.doubanio.com/view/movie_poster_cover/spst/public/p2187896711.jpg
         */

        var small: String? = null
        var large: String? = null
        var medium: String? = null
    }

    class CastsBean {
        /**
         * alt : https://movie.douban.com/celebrity/1343010/
         * avatars : {"small":"http://img7.doubanio.com/img/celebrity/small/CictjBsUJs0cel_avatar_uploaded1410769575.65.jpg","large":"http://img7.doubanio.com/img/celebrity/large/CictjBsUJs0cel_avatar_uploaded1410769575.65.jpg","medium":"http://img7.doubanio.com/img/celebrity/medium/CictjBsUJs0cel_avatar_uploaded1410769575.65.jpg"}
         * name : 艾米丽·芭尔多尼
         * id : 1343010
         */

        var alt: String? = null
        var avatars: AvatarsBean? = null
        var name: String? = null
        var id: String? = null

        class AvatarsBean {
            /**
             * small : http://img7.doubanio.com/img/celebrity/small/CictjBsUJs0cel_avatar_uploaded1410769575.65.jpg
             * large : http://img7.doubanio.com/img/celebrity/large/CictjBsUJs0cel_avatar_uploaded1410769575.65.jpg
             * medium : http://img7.doubanio.com/img/celebrity/medium/CictjBsUJs0cel_avatar_uploaded1410769575.65.jpg
             */

            var small: String? = null
            var large: String? = null
            var medium: String? = null
        }
    }

    class DirectorsBean {
        /**
         * alt : https://movie.douban.com/celebrity/1342405/
         * avatars : {"small":"http://img3.doubanio.com/img/celebrity/small/f4uD1Ah3aRscel_avatar_uploaded1408558838.98.jpg","large":"http://img3.doubanio.com/img/celebrity/large/f4uD1Ah3aRscel_avatar_uploaded1408558838.98.jpg","medium":"http://img3.doubanio.com/img/celebrity/medium/f4uD1Ah3aRscel_avatar_uploaded1408558838.98.jpg"}
         * name : 詹姆斯·沃德·布柯特
         * id : 1342405
         */

        var alt: String? = null
        var avatars: AvatarsBeanX? = null
        var name: String? = null
        var id: String? = null

        class AvatarsBeanX {
            /**
             * small : http://img3.doubanio.com/img/celebrity/small/f4uD1Ah3aRscel_avatar_uploaded1408558838.98.jpg
             * large : http://img3.doubanio.com/img/celebrity/large/f4uD1Ah3aRscel_avatar_uploaded1408558838.98.jpg
             * medium : http://img3.doubanio.com/img/celebrity/medium/f4uD1Ah3aRscel_avatar_uploaded1408558838.98.jpg
             */

            var small: String? = null
            var large: String? = null
            var medium: String? = null
        }
    }
}
