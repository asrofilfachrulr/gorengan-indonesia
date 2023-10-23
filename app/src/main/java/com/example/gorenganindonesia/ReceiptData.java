package com.example.gorenganindonesia;

import java.util.ArrayList;

public class ReceiptData {
    public static ArrayList<Receipt> generate(){
        ArrayList<Receipt> receipts = new ArrayList<>();

        receipts.add(
                new Receipt(
                        "Cireng Pedas Manis",
                        "Aci",
                        30,
                        R.drawable.cireng,
                        "Mudah",
                        3,
                        new String[]{
                                "Campur tepung kanji, tepung terigu, bawang putih, garam, gula, dan kaldu ayam bubuk (jika digunakan) dalam mangkuk besar.",
                                "Tambahkan air perlahan-lahan sambil terus diaduk hingga membentuk adonan yang kental dan elastis. Pastikan tidak ada gumpalan.",
                                "Bentuk adonan menjadi silinder panjang dan potong-potong menjadi bagian berukuran sekitar 5 cm.",
                                "Panaskan minyak dalam wajan hingga cukup panas. Goreng cireng hingga kecokelatan dan mengembang, sekitar 4-5 menit.",
                                "Angkat cireng dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak.",
                                "Untuk bumbu pedas manis (opsional), campur semua bahan bumbu dalam mangkuk kecil dan aduk rata.",
                                "Celupkan cireng dalam bumbu pedas manis atau sajikan bumbu tersebut sebagai saus."
                        },
                        new Ingredients[]{
                                new Ingredients("250", "gram", "tepung kanji"),
                                new Ingredients("2", "siung", "bawang putih, haluskan"),
                                new Ingredients("½", "sdt", "garam"),
                                new Ingredients("¼", "sdt", "gula"),
                                new Ingredients("¼", "sdt", "kaldu bubuk (opsional)"),
                                new Ingredients("200", "ml", "air"),
                                new Ingredients("2", "sdm", "minyak goreng"),
                                new Ingredients("3", "sdm", "saus sambal manis (untuk bumbu pedas manis)"),
                                new Ingredients("1", "sdm", "saus tomat (untuk bumbu pedas manis)"),
                                new Ingredients("1", "sdm", "kecap manis (untuk bumbu pedas manis)"),
                                new Ingredients("1", "sdt", "gula (untuk bumbu pedas manis)"),
                                new Ingredients("½", "sdt", "garam (untuk bumbu pedas manis)"),
                                new Ingredients("1", "sdm", "air (untuk bumbu pedas manis)")

                        }
                )
        );

        receipts.add(
                new Receipt(
                        "Tempe Mendoan Crispy",
                        "Kedelai",
                        30,
                        R.drawable.mendoan,
                        "Mudah",
                        6,
                        new String[]{
                                "Campur tepung terigu, tepung beras, merica bubuk, dan garam dalam mangkuk.",
                                "Tambahkan air es ke dalam campuran tepung sambil terus diaduk hingga membentuk adonan yang kental.",
                                "Celupkan potongan tempe ke dalam adonan tepung.",
                                "Panaskan minyak dalam wajan dan goreng tempe yang telah dicelupkan ke dalam adonan tepung hingga kecokelatan dan renyah, sekitar 3-5 menit."
                        },
                        new Ingredients[]{
                                new Ingredients("200", "gram", "tempe, potong tipis"),
                                new Ingredients("150", "gram", "tepung terigu"),
                                new Ingredients("2", "sdm", "tepung beras"),
                                new Ingredients("½", "sdt", "merica bubuk"),
                                new Ingredients("¼", "sdt", "garam"),
                                new Ingredients("200", "ml", "air es")
                        }
                )
        );

        receipts.add(
                new Receipt(
                        "Lemper Lezat Isi Ayam",
                        "Ketan Isi",
                        60,
                        R.drawable.lemper,
                        "Sedang",
                        8,
                        new String[]{
                                "Bersihkan dan kukus beras ketan hingga matang dan lengket.",
                                "Rebus ayam bersama bumbu hingga empuk dan bumbu meresap. Angkat ayam, dinginkan, dan suwir-suwir dagingnya.",
                                "Panaskan minyak dalam wajan dan tumis bumbu halus hingga harum. Masukkan ayam suwir dan aduk rata.",
                                "Ambil selembar daun pisang, letakkan sebagian ketan di atasnya, ratakan, tambahkan sebagian isi ayam, lalu tutup dengan ketan lagi.",
                                "Bungkus rapat menggunakan daun pisang. Ulangi hingga bahan habis.",
                                "Kukus lemper dalam kukusan panas selama 15-20 menit hingga matang.",
                                "Sajikan lemper hangat dengan bumbu kacang."
                        },
                        new Ingredients[]{
                                new Ingredients("500", "gram", "beras ketan"),
                                new Ingredients("400", "gram", "ayam, potong-potong"),
                                new Ingredients("2", "lembar", "daun pisang"),
                                new Ingredients("2", "sdm", "minyak goreng"),
                                new Ingredients("2", "batang", "serai, memarkan"),
                                new Ingredients("3", "lembar", "daun jeruk, iris halus"),
                                new Ingredients("1", "lembar", "daun salam"),
                                new Ingredients("½", "sdt", "garam"),
                                new Ingredients("¼", "sdt", "merica bubuk"),
                                new Ingredients("", "secukupnya", "air")
                        }
                )
        );

        receipts.add(
                new Receipt(
                        "Pisang Goreng Abang-Abang",
                        "Buah",
                        30,
                        R.drawable.pisang_goreng,
                        "Mudah",
                        10,
                        new String[]{
                                "Kupas pisang dan potong sesuai selera.",
                                "Campur tepung terigu, tepung beras, garam, gula, dan air hingga membentuk adonan yang kental dan melekat pada pisang.",
                                "Panaskan minyak dalam wajan hingga cukup panas.",
                                "Celupkan potongan pisang ke dalam adonan tepung dan goreng hingga kecokelatan, sekitar 3-5 menit.",
                                "Angkat pisang goreng dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak."
                        },
                        new Ingredients[]{
                                new Ingredients("4", "buah", "pisang, potong sesuai selera"),
                                new Ingredients("150", "gram", "tepung terigu"),
                                new Ingredients("50", "gram", "tepung beras"),
                                new Ingredients("½", "sdt", "garam"),
                                new Ingredients("2", "sdm", "gula"),
                                new Ingredients("200", "ml", "air"),
                                new Ingredients("", "secukupnya", "minyak goreng")
                        }
                )
        );

        receipts.add(
                new Receipt(
                        "Kentang Goreng Hot",
                        "Kentang",
                        45,
                        R.drawable.kentang_goreng_pedas,
                        "Sedang",
                        40,
                        new String[]{
                                "Kupas kentang dan potong sesuai selera.",
                                "Campurkan tepung terigu, merica bubuk, garam, dan bawang putih bubuk dalam mangkuk.",
                                "Celupkan potongan kentang ke dalam adonan tepung hingga rata.",
                                "Panaskan minyak dalam wajan hingga cukup panas.",
                                "Goreng kentang hingga kecokelatan dan renyah, sekitar 5-7 menit.",
                                "Tiriskan kentang goreng di atas tisu dapur untuk menghilangkan kelebihan minyak.",
                                "Sajikan kentang goreng dengan saus pedas sebagai pelengkap."
                        },
                        new Ingredients[]{
                                new Ingredients("4", "buah", "kentang, potong sesuai selera"),
                                new Ingredients("150", "gram", "tepung terigu"),
                                new Ingredients("½", "sdt", "merica bubuk"),
                                new Ingredients("½", "sdt", "garam"),
                                new Ingredients("½", "sdt", "bawang putih bubuk"),
                                new Ingredients("", "secukupnya", "minyak untuk menggoreng"),
                                new Ingredients("", "secukupnya","saus pedas sebagai pelengkap")
                        }
                )
        );

        receipts.add(
                new Receipt(
                        "Peyek Udang",
                        "Ikan",
                        45,
                        R.drawable.peyek_udang,
                        "Sedang",
                        20,
                        new String[]{
                                "Campur tepung beras, tepung terigu, udang, air, bawang putih, garam, gula, dan kaldu bubuk dalam mangkuk hingga membentuk adonan yang kental dan melekat pada udang.",
                                "Panaskan minyak dalam wajan hingga cukup panas.",
                                "Celupkan udang ke dalam adonan tepung dan goreng hingga kecokelatan, sekitar 3-5 menit.",
                                "Angkat peyek udang dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak."
                        },
                        new Ingredients[]{
                                new Ingredients("200", "gram", "tepung beras"),
                                new Ingredients("50", "gram", "tepung terigu"),
                                new Ingredients("100", "gram", "udang, kupas, cuci bersih, dan tiriskan"),
                                new Ingredients("150", "ml", "air es"),
                                new Ingredients("2", "siung", "bawang putih, haluskan"),
                                new Ingredients("1/2", "sdt", "garam"),
                                new Ingredients("1/2", "sdt", "gula"),
                                new Ingredients("1/4", "sdt", "kaldu bubuk"),
                                new Ingredients("", "secukupnya", "minyak untuk menggoreng"),
                        }
                )
        );

        receipts.add(
                new Receipt(
                        "Bakwan Renyah dan Gurih",
                        "Isian Sayur",
                        40,
                        R.drawable.bakwan,
                        "Mudah",
                        10,
                        new String[]{
                                "Campur tepung terigu, air, bawang putih, garam, gula, kaldu bubuk, dan garam dalam mangkuk hingga membentuk adonan yang kental.",
                                "Tambahkan tauge, wortel, kol, dan daun bawang ke dalam adonan. Aduk rata.",
                                "Panaskan minyak dalam wajan hingga cukup panas.",
                                "Ambil satu sendok sayur adonan dan goreng hingga kecokelatan, sekitar 3-5 menit.",
                                "Angkat bakwan dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak."
                        },
                        new Ingredients[]{
                                new Ingredients("150", "gram", "tepung terigu"),
                                new Ingredients("200", "ml", "air"),
                                new Ingredients("2", "siung", "bawang putih, haluskan"),
                                new Ingredients("1/2", "sdt", "garam"),
                                new Ingredients("1/2", "sdt", "gula"),
                                new Ingredients("1/4", "sdt", "kaldu bubuk"),
                                new Ingredients("1/2", "sdt", "garam"),
                                new Ingredients("", "secukupnya", "tauge"),
                                new Ingredients("", "secukupnya", "wortel, potong korek api"),
                                new Ingredients("", "secukupnya", "kol, iris halus"),
                                new Ingredients("", "secukupnya", "daun bawang, iris halus"),
                                new Ingredients("", "secukupnya", "minyak untuk menggoreng")
                        }
                )
        );

        receipts.add(
                new Receipt(
                        "Gorengan Ubi Jalar",
                        "Manis",
                        40, // estimated cooking duration in minutes
                        R.drawable.ubi_jalar,
                        "Mudah",
                        5,
                        new String[]{
                                "Kupas ubi jalar dan iris tipis-tipis.",
                                "Campurkan tepung terigu, bawang putih bubuk, garam, merica, dan air hingga membentuk adonan yang kental.",
                                "Panaskan minyak dalam wajan hingga cukup panas.",
                                "Celupkan irisan ubi jalar ke dalam adonan tepung dan goreng hingga kecokelatan, sekitar 3-5 menit.",
                                "Angkat gorengan ubi jalar dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak."
                        },
                        new Ingredients[]{
                                new Ingredients("500", "gram", "ubi jalar, kupas dan iris tipis"),
                                new Ingredients("150", "gram", "tepung terigu"),
                                new Ingredients("1/2", "sdt", "bawang putih bubuk"),
                                new Ingredients("1/2", "sdt", "garam"),
                                new Ingredients("1/4", "sdt", "merica"),
                                new Ingredients("150", "ml", "air"),
                                new Ingredients("", "secukupnya", "minyak untuk menggoreng")
                        }
                )
        );

        receipts.add(
                new Receipt(
                        "Perkedel",
                        "Kentang",
                        40, // estimated cooking duration in minutes
                        R.drawable.perkedel,
                        "Sedang",
                        7,
                        new String[]{
                                "Kupas kentang dan rebus hingga empuk. Haluskan kentang yang sudah direbus.",
                                "Campurkan kentang yang sudah dihaluskan, daging ayam cincang, bawang putih, bawang merah, telur, garam, merica, dan penyedap rasa dalam mangkuk hingga merata.",
                                "Bentuk adonan menjadi bulatan dan pipihkan.",
                                "Panaskan minyak dalam wajan hingga cukup panas.",
                                "Goreng perkedel hingga kecokelatan, sekitar 3-5 menit di setiap sisi.",
                                "Angkat perkedel dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak."
                        },
                        new Ingredients[]{
                                new Ingredients("500", "gram", "kentang, kupas dan rebus hingga empuk"),
                                new Ingredients("200", "gram", "daging ayam cincang"),
                                new Ingredients("2", "siung", "bawang putih, haluskan"),
                                new Ingredients("4", "butir", "bawang merah, cincang halus"),
                                new Ingredients("1", "butir", "telur"),
                                new Ingredients("1/2", "sdt", "garam"),
                                new Ingredients("1/4", "sdt", "merica"),
                                new Ingredients("", "secukupnya", "penyedap rasa secukupnya"),
                                new Ingredients("", "secukupnya", "minyak untuk menggoreng")
                        }
                )
        );

        receipts.add(
                new Receipt(
                        "Tahu Isi",
                        "Kedelai",
                        30, // estimated cooking duration in minutes
                        R.drawable.tahu_isi,
                        "Mudah",
                        8,
                        new String[]{
                                "Potong tahu menjadi kotak-kotak kecil.",
                                "Campurkan tepung terigu, bawang putih bubuk, garam, merica, dan air hingga membentuk adonan yang kental.",
                                "Buka lubang di setiap potongan tahu dan isi dengan adonan.",
                                "Panaskan minyak dalam wajan hingga cukup panas.",
                                "Goreng tahu hingga kecokelatan dan renyah, sekitar 3-5 menit.",
                                "Angkat tahu isi dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak."
                        },
                        new Ingredients[]{
                                new Ingredients("4", "buah", "tahu, potong menjadi kotak-kotak kecil"),
                                new Ingredients("150", "gram", "tepung terigu"),
                                new Ingredients("1/2", "sdt", "bawang putih bubuk"),
                                new Ingredients("1/2", "sdt", "garam"),
                                new Ingredients("1/4", "sdt", "merica"),
                                new Ingredients("200", "ml", "air"),
                                new Ingredients("", "secukupnya", "minyak untuk menggoreng")
                        }
                )
        );

        receipts.add(
                new Receipt(
                        "Molen",
                        "Manis",
                        40, // estimated cooking duration in minutes
                        R.drawable.molen,
                        "Sulit",
                        10,
                        new String[]{
                                "Campurkan tepung terigu, mentega, gula, garam, dan telur dalam mangkuk hingga membentuk adonan kalis.",
                                "Pisahkan adonan menjadi beberapa bagian kecil. Giling setiap bagian adonan menjadi lembaran tipis.",
                                "Olesi lembaran adonan dengan selai nanas.",
                                "Gulung lembaran adonan seperti gulungan sushi dan potong-potong sesuai selera.",
                                "Panaskan minyak dalam wajan hingga cukup panas.",
                                "Goreng molen hingga kecokelatan, sekitar 3-5 menit.",
                                "Angkat molen dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak."
                        },
                        new Ingredients[]{
                                new Ingredients("250", "gram", "tepung terigu"),
                                new Ingredients("100", "gram", "mentega"),
                                new Ingredients("50", "gram", "gula"),
                                new Ingredients("1/2", "sdt", "garam"),
                                new Ingredients("1", "butir", "telur"),
                                new Ingredients("", "secukupnya", "selai nanas"),
                                new Ingredients("", "secukupnya", "minyak untuk menggoreng")
                        }
                )
        );

        return receipts;
    }
}
