package com.example.submissionjetpack.utils

import com.example.submissionjetpack.data.local.MovieEntity
import com.example.submissionjetpack.data.local.TvShowEntity

object DataDummy {
    fun dataMovies(): List<MovieEntity> {
        val movies = ArrayList<MovieEntity>()
        movies.add(
            MovieEntity(
                0,
                "A Star Is Born",
                "2018",
                "October 3",
                "Drama, Romance, Music",
                3.75,
                "136 Minutes",
                "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
                "https://image.tmdb.org/t/p/w300/wrFpXMNBRj2PBiN4Z5kix51XaIZ.jpg",
                "https://image.tmdb.org/t/p/w500/mnDvPokXpvsdPcWSjNRPhiiLOKu.jpg"
            )
        )

        movies.add(
            MovieEntity(
                1,
                "Alita: Battle Angel",
                "2019",
                "January 31",
                "Action, Science Fiction, Adventure",
                3.6,
                "122 Minutes",
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                "https://image.tmdb.org/t/p/w300/xRWht48C2V8XNfzvPehyClOvDni.jpg",
                "https://image.tmdb.org/t/p/w500/8RKBHHRqOMOLh5qW3sS6TSFTd8h.jpg"
            )
        )

        movies.add(
            MovieEntity(
                2,
                "Aquaman",
                "2018",
                "December 7",
                "Action, Adventure, Fantasy",
                3.45,
                "143 Minutes",
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "https://image.tmdb.org/t/p/w300/xLPffWMhMj1l50ND3KchMjYoKmE.jpg",
                "https://image.tmdb.org/t/p/w500/9QusGjxcYvfPD1THg6oW3RLeNn7.jpg"
            )
        )

        movies.add(
            MovieEntity(
                3,
                "Bohemian Rhapsody",
                "2018",
                "October 24",
                "Music, Drama, History",
                4.0,
                "135 Minutes",
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                "https://image.tmdb.org/t/p/w300/lHu1wtNaczFPGFDTrjCSzeLPTKN.jpg",
                "https://image.tmdb.org/t/p/w500/jNUpYq2jRSwQM89vST9yQZelMSu.jpg"
            )
        )

        movies.add(
            MovieEntity(
                4,
                "Cold Pursuit",
                "2019",
                "February 7",
                "Action, Crime, Thriller",
                2.85,
                "119 Minutes",
                "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
                "https://image.tmdb.org/t/p/w300/hXgmWPd1SuujRZ4QnKLzrj79PAw.jpg",
                "https://image.tmdb.org/t/p/w500/aiM3XxYE2JvW1vJ4AC6cI1RjAoT.jpg"
            )
        )

        movies.add(
            MovieEntity(
                5,
                "Creed II",
                "2018",
                "November 21",
                "Drama",
                3.45,
                "130 Minutes",
                "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
                "https://image.tmdb.org/t/p/w300/v3QyboWRoA4O9RbcsqH8tJMe8EB.jpg",
                "https://image.tmdb.org/t/p/w500/9il7qNbeYnPMYlutsVYDsj4hRyb.jpg"
            )
        )

        movies.add(
            MovieEntity(
                6,
                "Fantastic Beasts: The Crimes of Grindelwald",
                "2018",
                "November 14",
                "Adventure, Fantasy, Drama",
                3.45,
                "134 Minutes",
                "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.",
                "https://image.tmdb.org/t/p/w300/fMMrl8fD9gRCFJvsx0SuFwkEOop.jpg",
                "https://image.tmdb.org/t/p/w500/qrtRKRzoNkf5vemO9tJ2Y4DrHxQ.jpg"
            )
        )

        movies.add(
            MovieEntity(
                7,
                "Glass",
                "2019",
                "January 16",
                "Thriller, Drama, Science Fiction",
                3.35,
                "129 Minutes",
                "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
                "https://image.tmdb.org/t/p/w300/svIDTNUoajS8dLEo7EosxvyAsgJ.jpg",
                "https://image.tmdb.org/t/p/w500/ngBFDOsx13sFXiMweDoL54XYknR.jpg"
            )
        )

        movies.add(
            MovieEntity(
                8,
                "How to Train Your Dragon: The Hidden World",
                "2019",
                "January 3",
                "Animation, Family, Adventure",
                3.9,
                "104 Minutes",
                "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
                "https://image.tmdb.org/t/p/w300/xvx4Yhf0DVH8G4LzNISpMfFBDy2.jpg",
                "https://image.tmdb.org/t/p/w500/lFwykSz3Ykj1Q3JXJURnGUTNf1o.jpg"
            )
        )

        movies.add(
            MovieEntity(
                9,
                "Avengers: Infinity War",
                "2018",
                "April 25",
                "Adventure, Action, Science Fiction",
                4.15,
                "149 Minutes",
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                "https://image.tmdb.org/t/p/w300/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg",
                "https://image.tmdb.org/t/p/w500/lmZFxXgJE3vgrciwuDib0N8CfQo.jpg"
            )
        )

        return movies
    }

    fun dataTvShows(): List<TvShowEntity> {
        val tvShows = ArrayList<TvShowEntity>()
        tvShows.add(
            TvShowEntity(
                0,
                "Arrow",
                "2012",
                "October 10",
                "Crime, Drama, Mystery, Action & Adventure",
                3.3,
                "42 Minutes",
                "8 Seasons",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "https://image.tmdb.org/t/p/w300/gKG5QGz5Ngf8fgWpBsWtlg5L2SF.jpg",
                "https://image.tmdb.org/t/p/w500/vNnLAKmoczRlNarxyGrrw0KSOeX.jpg"
            )
        )

        tvShows.add(
            TvShowEntity(
                1,
                "Doom Patrol",
                "2019",
                "February 15",
                "Sci-Fi & Fantasy, Comedy, Drama",
                3.8,
                "49 Minutes",
                "2 Seasons",
                "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
                "https://image.tmdb.org/t/p/w300/drlfSCIlMKrEeMPhi8pqY4xGxj.jpg",
                "https://image.tmdb.org/t/p/w500/b7unxzWSFfj7i6UVmuPixxxXFOF.jpg"
            )
        )

        tvShows.add(
            TvShowEntity(
                2,
                "Dragon Ball",
                "1986",
                "February 26",
                "Animation, Action & Adventure, Sci-Fi & Fantasy",
                4.05,
                "25 Minutes",
                "1 Season",
                "Long ago in the mountains, a fighting master known as Gohan discovered a strange boy whom he named Goku. Gohan raised him and trained Goku in martial arts until he died. The young and very strong boy was on his own, but easily managed. Then one day, Goku met a teenage girl named Bulma, whose search for the mystical Dragon Balls brought her to Goku's home. Together, they set off to find all seven and to grant her wish.",
                "https://image.tmdb.org/t/p/w300/ykl67ghR2ug6KGFH3CQcI01pzQJ.jpg",
                "https://image.tmdb.org/t/p/w500/di2tb6a8SkvEVmRcVqgVXmuQiOo.jpg"
            )
        )

        tvShows.add(
            TvShowEntity(
                3,
                "Fairy Tail",
                "2009",
                "October 12",
                "Action & Adventure, Animation, Comedy, Sci-Fi & Fantasy, Mystery",
                3.9,
                "25 Minutes",
                "8 Seasons",
                "Lucy is a 17-year-old girl, who wants to be a full-fledged mage. One day when visiting Harujion Town, she meets Natsu, a young man who gets sick easily by any type of transportation. But Natsu isn't just any ordinary kid, he's a member of one of the world's most infamous mage guilds: Fairy Tail.",
                "https://image.tmdb.org/t/p/w300/jsYTctFnK8ewomnUgcwhmsTkOum.jpg",
                "https://image.tmdb.org/t/p/w500/fANxNeH9JCXPrzNEfriGu1Y95dF.jpg"
            )
        )

        tvShows.add(
            TvShowEntity(
                4,
                "Family Guy",
                "1999",
                "January 31",
                "Animation, Comedy",
                3.5,
                "22 Minutes",
                "19 Seasons",
                "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
                "https://image.tmdb.org/t/p/w300/eWWCRjBfLyePh2tfZdvNcIvKSJe.jpg",
                "https://image.tmdb.org/t/p/w500/buwzKwQXlqOAz3xL23lf6QM7qhB.jpg"
            )
        )

        tvShows.add(
            TvShowEntity(
                5,
                "Game of Thrones",
                "2011",
                "April 17",
                "Sci-Fi & Fantasy, Drama, Action, & Adventure",
                4.2,
                "60 Minutes",
                "8 Seasons",
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                "https://image.tmdb.org/t/p/w300/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
                "https://image.tmdb.org/t/p/w500/suopoADq0k8YZr4dQXcU6pToj6s.jpg"
            )
        )

        tvShows.add(
            TvShowEntity(
                6,
                "Gotham",
                "2014",
                "September 22",
                "Drama, Crime, Sci-Fi & Fantasy",
                3.75,
                "43 Minutes",
                "5 Seasons",
                "Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
                "https://image.tmdb.org/t/p/w300/4XddcRDtnNjYmLRMYpbrhFxsbuq.jpg",
                "https://image.tmdb.org/t/p/w500/4XddcRDtnNjYmLRMYpbrhFxsbuq.jpg"
            )
        )

        tvShows.add(
            TvShowEntity(
                7,
                "Grey's Anatomy",
                "2005",
                "March 27",
                "Drama",
                4.1,
                "43 Minutes",
                "17 Seasons",
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                "https://image.tmdb.org/t/p/w300/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
                "https://image.tmdb.org/t/p/w500/edmk8xjGBsYVIf4QtLY9WMaMcXZ.jpg"
            )
        )

        tvShows.add(
            TvShowEntity(
                8,
                "Hanna",
                "2019",
                "March 28",
                "Action & Adventure, Drama",
                3.75,
                "50 Minutes",
                "2 Seasons",
                "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.",
                "https://image.tmdb.org/t/p/w300/iYUtjx1EN4SVTgxd2TB4cZTGSQb.jpg",
                "https://image.tmdb.org/t/p/w500/6Fzyuwn8KeMCSqRILfdrDmCvmod.jpg"
            )
        )

        tvShows.add(
            TvShowEntity(
                9,
                "Marvel's Iron Fist",
                "2017",
                "March 17",
                "Action & Adventure, Drama, Sci-Fi & Fantasy",
                3.3,
                "55 Minutes",
                "2 Seasons",
                "Danny Rand resurfaces 15 years after being presumed dead. Now, with the power of the Iron Fist, he seeks to reclaim his past and fulfill his destiny.",
                "https://image.tmdb.org/t/p/w300/4l6KD9HhtD6nCDEfg10Lp6C6zah.jpg",
                "https://image.tmdb.org/t/p/w500/xHCfWGlxwbtMeeOnTvxUCZRGnkk.jpg"
            )
        )

        return tvShows
    }
}