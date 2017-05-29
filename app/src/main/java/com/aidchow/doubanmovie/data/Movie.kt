package com.aidchow.doubanmovie.data

/**
 * Created by aidchow on 17-5-26.
 * movie list
 */

class Movie {


    var count: Int = 0
    var start: Int = 0
    var total: Int = 0
    var title: String? = null
    var subjects: List<SubjectsBean>? = null

    class SubjectsBean {
        /**
         * rating : {"max":10,"average":9.6,"stars":"50","min":0}
         * genres : ["犯罪","剧情"]
         * title : 肖申克的救赎
         * casts : [{"alt":"https://movie.douban.com/celebrity/1054521/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/17525.jpg","large":"https://img3.doubanio.com/img/celebrity/large/17525.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/17525.jpg"},"name":"蒂姆·罗宾斯","id":"1054521"},{"alt":"https://movie.douban.com/celebrity/1054534/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/34642.jpg","large":"https://img3.doubanio.com/img/celebrity/large/34642.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/34642.jpg"},"name":"摩根·弗里曼","id":"1054534"},{"alt":"https://movie.douban.com/celebrity/1041179/","avatars":{"small":"https://img1.doubanio.com/img/celebrity/small/5837.jpg","large":"https://img1.doubanio.com/img/celebrity/large/5837.jpg","medium":"https://img1.doubanio.com/img/celebrity/medium/5837.jpg"},"name":"鲍勃·冈顿","id":"1041179"}]
         * collect_count : 1065793
         * original_title : The Shawshank Redemption
         * subtype : movie
         * directors : [{"alt":"https://movie.douban.com/celebrity/1047973/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/230.jpg","large":"https://img3.doubanio.com/img/celebrity/large/230.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/230.jpg"},"name":"弗兰克·德拉邦特","id":"1047973"}]
         * year : 1994
         * images : {"small":"https://img3.doubanio.com/view/movie_poster_cover/ipst/public/p480747492.jpg","large":"https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p480747492.jpg","medium":"https://img3.doubanio.com/view/movie_poster_cover/spst/public/p480747492.jpg"}
         * alt : https://movie.douban.com/subject/1292052/
         * id : 1292052
         */

        var rating: RatingBean? = null
        var title: String? = null
        var collect_count: Int = 0
        var original_title: String? = null
        var subtype: String? = null
        var year: String? = null
        var images: ImagesBean? = null
        var alt: String? = null
        var id: String? = null
        var genres: List<String>? = null
        var casts: List<CastsBean>? = null
        var directors: List<DirectorsBean>? = null

        class RatingBean {
            /**
             * max : 10
             * average : 9.6
             * stars : 50
             * min : 0
             */

            var max: Int = 0
            var average: Double = 0.toDouble()
            var stars: String? = null
            var min: Int = 0
        }

        class ImagesBean {
            /**
             * small : https://img3.doubanio.com/view/movie_poster_cover/ipst/public/p480747492.jpg
             * large : https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p480747492.jpg
             * medium : https://img3.doubanio.com/view/movie_poster_cover/spst/public/p480747492.jpg
             */

            var small: String? = null
            var large: String? = null
            var medium: String? = null
        }

        class CastsBean {
            /**
             * alt : https://movie.douban.com/celebrity/1054521/
             * avatars : {"small":"https://img3.doubanio.com/img/celebrity/small/17525.jpg","large":"https://img3.doubanio.com/img/celebrity/large/17525.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/17525.jpg"}
             * name : 蒂姆·罗宾斯
             * id : 1054521
             */

            var alt: String? = null
            var avatars: AvatarsBean? = null
            var name: String? = null
            var id: String? = null

            class AvatarsBean {
                /**
                 * small : https://img3.doubanio.com/img/celebrity/small/17525.jpg
                 * large : https://img3.doubanio.com/img/celebrity/large/17525.jpg
                 * medium : https://img3.doubanio.com/img/celebrity/medium/17525.jpg
                 */

                var small: String? = null
                var large: String? = null
                var medium: String? = null
            }
        }

        class DirectorsBean {
            /**
             * alt : https://movie.douban.com/celebrity/1047973/
             * avatars : {"small":"https://img3.doubanio.com/img/celebrity/small/230.jpg","large":"https://img3.doubanio.com/img/celebrity/large/230.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/230.jpg"}
             * name : 弗兰克·德拉邦特
             * id : 1047973
             */

            var alt: String? = null
            var avatars: AvatarsBeanX? = null
            var name: String? = null
            var id: String? = null

            class AvatarsBeanX {
                /**
                 * small : https://img3.doubanio.com/img/celebrity/small/230.jpg
                 * large : https://img3.doubanio.com/img/celebrity/large/230.jpg
                 * medium : https://img3.doubanio.com/img/celebrity/medium/230.jpg
                 */

                var small: String? = null
                var large: String? = null
                var medium: String? = null
            }
        }

        override fun toString(): String {
            return "SubjectsBean(rating=$rating, title=$title, collect_count=$collect_count, original_title=$original_title, subtype=$subtype, year=$year, images=$images, alt=$alt, id=$id, genres=$genres, casts=$casts, directors=$directors)"
        }
    }

    override fun toString(): String {
        return "Movie(count=$count, start=$start, total=$total, title=$title, subjects=$subjects)"
    }

}
