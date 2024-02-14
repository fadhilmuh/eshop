# Refleksi
```
Fadhil Muhammad
2206083464
```

# MODUL 2
<details>
<summary style="font-size:20px">Refleksi </summary>

## List Code Quality Issue yang Di-fix
1. #### Menghapus modifier public pada interface ProductService.java.
    
    Modifier method-method pada interface ProductService.java dihapus karena merupakan hal yang tidak penting. Dengan menghapusnya meningkatkan kualitas code style
2. #### Menghapus import .* pada ProductController.java dan mengubahnya untuk mengimport hal-hal yang diperlukan saja

    Hal ini dilakukan karena tidak semua yang diimport digunakan. Sehingga, mengubah import .* menjadi import-import modul yang dipakai saja meningkatkan kualitas code style.

    Sebelum:
    ```java
    ...
    import org.springframework.web.bind.annotation.*
    ...
    ```

    Sesudah:
    ```java
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.ModelAttribute;
    import org.springframework.web.bind.annotation.PathVariable;
    import org.springframework.web.bind.annotation.DeleteMapping;
    ```

### Apakah implementasi sekarang sudah memenuhi definisi CI/CD (Continuous Integration and Continuous Development)?


Penerapan Continuous Integration/Continuous Deployment (CI/CD) merupakan praktik penting dalam pengembangan perangkat lunak modern. Dengan CI/CD, proses pengembangan dapat diotomatisasi secara efisien, memungkinkan kita untuk melakukan uji coba dan deployment secara cepat dan konsisten setiap kali ada perubahan kode.

Menurut saya, penerapan saat ini telah mencapai prinsip dasar CI/CD dengan baik. Dengan menggunakan GitHub Workflows, proyek dapat melakukan uji coba dan deployment secara otomatis setiap kali ada perubahan di repositori GitHub. Uji coba dilakukan menggunakan file ci.yml untuk setiap perubahan di semua branch, kemudian kode diperiksa menggunakan PMD untuk memastikan keamanan dan kualitasnya. Setelah lulus uji coba dan dinyatakan aman, kode akan digabungkan ke branch utama dan secara otomatis didistribusikan ke PaaS Koyeb melalui file scorecard.yml. Semua proses ini membentuk alur kerja CI/CD yang otomatis dalam siklus pengembangan perangkat lunak.
</details>

# 
# MODUL 1
<details>
<summary style="font-size:20px">Refleksi 1</summary>

# 
## REFLEKSI-1: Clean Code
Clean code adalah konsep dalam pengembangan perangkat lunak yang menekankan pentingnya menulis kode yang mudah dibaca, dimengerti, dan dipelihara oleh orang lain. Prinsip-prinsip clean code membantu mengurangi kompleksitas, meningkatkan keterbacaan, dan memudahkan pemeliharaan kode.

Dalam tutorial modul 1 kali ini, beberapa praktik clean code yang telah diterapkan antara lain:

### 1. Meaningful Variable Names
Nama variabel seperti `product` dan `productId` dengan jelas menyampaikan tujuan dari variabel-variabel tersebut. 

### 2. Function
Berikut contoh penggunaan function pada tutorial kali ini:
```java
...
    public Product create (Product product) {
        productData.add(product);
        productMap.put(product.getProductId(), product);
        return product;
    }
...
```
Penggunaan function-function dan penerapan functional programming ada dalam pengerjaan tutorial modul 1 kali ini. Function-function yang dibuat semuanya tepat untuk meng-handle satu bagian program saja, tidak terlalu panjang, dan nama-namanya jelas mendeskripsikan apa yang dilakukan di function tersebut.

### 3. Comments
Penambahan comments juga diterapkan pada tutorial kali ini untuk menjelaskan program.

### 4. Objects and Data Structures
Contoh penerapannya dalam tutorial ini adalah dari fungsi yang telah saya buat, saya menerapkan interface dan implementasinya di direktori service sebagai praktik terbaik dan untuk lebih rinci menjelaskan fungsi yang diimplementasikan.

### 5. Git Flow - Branching
Pada tutorial ini, saya telah menerapkan branching untuk memisahkan pengembangan masing-masing bagian seperti list-product, edit-product, delete-product, dll.

### 6. Unit Test
Saya juga telah menerapkan unit test untuk mengotomasi pengujian software yang telah saya buat pada tutorial kali ini.
</details>

<details>
<summary style="font-size:20px">Refleksi 2</summary>

#
## REFLEKSI-2: Unit Test

# 1
Setelah menulis unit test, saya merasa lebih percaya diri tentang kualitas kode yang saya tulis. Unit test membantu saya memastikan bahwa setiap bagian kecil dari kode berfungsi seperti yang diharapkan dan memberikan perlindungan terhadap perubahan yang tidak disengaja.

Tentang jumlah unit test yang harus dibuat dalam sebuah kelas, tidak ada jawaban pasti karena hal itu tergantung pada kompleksitas class dan fungsionalitas yang harus diuji. Namun, setiap metode yang memiliki logika yang berbeda atau perlu diuji secara terpisah sebaiknya memiliki setidaknya satu unit test.

Untuk memastikan bahwa unit test sudah cukup untuk memverifikasi program, penggunaan code coverage bisa menjadi alat yang berguna. Meskipun memiliki 100% code coverage bisa memberikan kepercayaan tambahan, namun itu tidak menjamin bahwa kode tidak memiliki bug atau kesalahan. Meskipun kode sudah diuji dengan baik, masih mungkin terdapat kasus-kasus edge atau situasi yang tidak terduga yang tidak tercakup dalam unit test.

# 2
Mengenai pembuatan functional test suite baru untuk memverifikasi jumlah item dalam daftar produk, penting untuk memperhatikan kebersihan kode. Duplikasi atau pengulangan kode dan kurangnya modularitas dapat mengurangi kualitas kode secara keseluruhan. Jika ada setup procedures dan instance variables yang sama dengan functional test suite sebelumnya, ini menunjukkan adanya duplikasi kode. Jika setup procedures tidak dipisahkan ke dalam metode yang terpisah atau jika terlalu banyak instance variables yang digunakan secara global, maka kode mungkin kurang modular. Sebaiknya dilakukan pemisahan setup procedures ke dalam metode terpisah untuk menghindari duplikasi kode dan meningkatkan modularitas.

</details>