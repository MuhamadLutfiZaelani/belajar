import java.util.Scanner;
import java.util.ArrayList;

class Buku {
    String judul;
    String penulis;


    public Buku(String judul, String penulis) {
        this.judul = judul;
        this.penulis = penulis;

    }

    public String toString() {
        return "Judul: " + judul + ", Penulis: " + penulis;
    }
}

//Class User (Parent Class)
class User {
 String nama;
 String id;

 public User(String nama, String id) {
     this.nama = nama;
     this.id = id;
 }

 public String toString() {
     return "Nama: " + nama + ", ID: " + id;
 }
}

//Kelas Admin (Child Class)
class Admin extends User {
 public Admin(String nama, String id) {
     super(nama, id);
 }

 public void tambahBuku(Perpustakaan perpustakaan, Buku buku) {
     perpustakaan.tambahBuku(buku);
 }

 public void hapusBuku(Perpustakaan perpustakaan, String judul) {
     perpustakaan.hapusBuku(judul);
 }
}

//Kelas Member (Child Class)
class Member extends User {
 public Member(String nama, String id) {
     super(nama, id);
 }

 public void cariBuku(Perpustakaan perpustakaan, String judul) {
     perpustakaan.cariBuku(judul);
 }

 public void tampilkanBuku(Perpustakaan perpustakaan) {
     perpustakaan.tampilkanBuku();
 }
 public void pinjamBuku(Perpustakaan perpustakaan, String judul) {
     perpustakaan.pinjamBuku(judul);
 }

 public void kembalikanBuku(Perpustakaan perpustakaan, String judul) {
     perpustakaan.kembalikanBuku(judul);

 }
}

//Kelas Perpustakaan Buku

class Perpustakaan {
    ArrayList<Buku> daftarBuku;
    ArrayList<Buku> daftarPinjam;

    public Perpustakaan(int kapasitas) {
        // Kapasitas hanya digunakan sebagai inital capacity
        daftarBuku = new ArrayList<>(kapasitas);
        daftarPinjam = new ArrayList<>(kapasitas);
    }
    
 // Menambahkan buku
 // Time Complexity: 0(1) -> Operasi penambahan hanya dilakukan satu kali di index tertentu (konstan)
 // Space Complexity: 0(1) -> Tidak menambah struktur data baru, hanya menambah elemen ke array yang sudah ada 
    public void tambahBuku(Buku buku) {
        daftarBuku.add(buku);
        System.out.println("Buku berhasil ditambahkan.");
    }

  

 // Menghapus buku
 // Time Complexity: 0(n) -> Terburuknya: harus mencari di seluruh array (O(n)), lalu melakukan pergeseran elemen sisa (O(n)), total tetap O(n)
 // Space Complexity: 0(1) -> Tidak membuat array baru; hanya memindahkan elemen di tempat
    public void hapusBuku(String judul) {
        for (int i = 0; i < daftarBuku.size(); i++) {
            if (daftarBuku.get(i).judul.equalsIgnoreCase(judul)) {
                daftarBuku.remove(i);
                System.out.println("Buku berhasil dihapus.");
                return;
            }
        }
        System.out.println("Buku tidak ditemukan.");
    }


 // Mencari buku berdasarkan judul
 // Time Complexity: 0(n) -> Harus mengecek satu per satu seluruh array
 // Space Complexity: 0(1) -> Tidak ada alokasi tambahan
    public void cariBuku(String judul) {
        boolean ditemukan = false;
        for (Buku b : daftarBuku) {
            if (b.judul.equalsIgnoreCase(judul)) {
                System.out.println("Buku ditemukan: " + b);
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            System.out.println("Buku tidak ditemukan.");
        }
    }

 // Menampilkan semua buku
 // Time Complexity: 0(n) -> Tergantung jumlah buku yang ada
 // Space Complexity: 0(1) -> Tidak menyimpan data baru, hanya mencetak
    public void tampilkanBuku() {
        System.out.println("\n--- Daftar Buku tersedia di Perpustakaan ---");
        if (daftarBuku.isEmpty()) {
            System.out.println("Tidak ada buku tersedia di perpustakaan.");
        } else {
            for (int i = 0; i < daftarBuku.size(); i++) {
                System.out.println((i+1)+ "." + daftarBuku.get(i));
            }
            System.out.println("----------------------");
        }

        System.out.println("\n--- Daftar Buku di Perpustakaan yang dipinjam---");
        if (daftarPinjam.isEmpty()) {
            System.out.println("Tidak ada buku  di perpustakaan yang dipinjam.");
        } else {
            for (int i = 0; i < daftarPinjam.size(); i++) {
                System.out.println((i+1)+ "." + daftarPinjam.get(i));
            }
            System.out.println("----------------------");
        }
        
    }

 // Meminjam buku

    public void pinjamBuku(String judul){
        for (int i = 0; i < daftarBuku.size(); i++){
            if(daftarBuku.get(i).judul.equalsIgnoreCase(judul)){
                Buku bukuDipinjam = daftarBuku.remove(i);
                daftarPinjam.add(bukuDipinjam);
                System.out.println("Buku '" + judul + "' berhasil dipinjam.");
                return;
            }
        }
        System.out.println("Gagal! Buku '" + judul + "' tidak tersedia atau tidak ditemukan.");
    }
    // Mengembalikan Buku
    public void kembalikanBuku(String judul){
        for (int i = 0; i < daftarPinjam.size(); i++){
            if(daftarPinjam.get(i).judul.equalsIgnoreCase(judul)){
                Buku bukuDikembalikan = daftarPinjam.remove(i);
                daftarBuku.add(bukuDikembalikan);
                System.out.println("Buku '" + judul + "' berhasil dikembalikan");
                return;
            }
        }
        System.out.println("Buku '" + judul + "' sedang tidak dipinjam/tidak ada");
    }
}



//Kelas Main (Main Program)
// Time Complexity -> Loop berjalan terus (infinite loop while (true)), tapi setiap opsi berisi operasi yang paling kompleks hanya O(n)
// Space Complexity -> Variabel lokal seperti judul, penulis, dan objek Scanner, Admin, Member, dll → O(1) secara total karena ukurannya tetap
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Perpustakaan perpustakaan = new Perpustakaan(100);

        perpustakaan.tambahBuku(new Buku("Belajar Git", "Aggresia Retha"));
        perpustakaan.tambahBuku(new Buku("From Zero to Advance C++", "Muhamad Lutfi Zaelani"));

        Admin admin = new Admin("Admin1", "A001");
        Member member = new Member("Member1", "M001");
        
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Menambahkan Buku Baru (Admin)");
            System.out.println("2. Menghapus Buku (Admin)");
            System.out.println("3. Mencari Buku Berdasarkan Judul (Member)");
            System.out.println("4. Tampilkan Semua Buku (Member)");
            System.out.println("5. Pinjam Buku (member)");
            System.out.println("6. Kembalikan Buku (member)");
            System.out.println("7. Keluar");
            System.out.print("Pilih opsi: ");

            int pilihan = -1;
            try {
                pilihan = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Input tidak valid! Harap masukkan angka.");
                scanner.next(); // Membersihkan buffer scanner
                continue;
            }
            scanner.nextLine(); // Membersihkan newline character dari buffer

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan Judul Buku: ");
                    String judul = scanner.nextLine();
                    System.out.print("Masukkan Penulis Buku: ");
                    String penulis = scanner.nextLine();
                    Buku buku = new Buku(judul, penulis);
                    admin.tambahBuku(perpustakaan, buku);
                    break;
                case 2:
                    System.out.print("Masukkan judul buku yang akan dihapus: ");
                    String judulHapus = scanner.nextLine();
                    admin.hapusBuku(perpustakaan, judulHapus);
                    break;
                case 3:
                    System.out.print("Masukkan judul buku yang dicari: ");
                    String judulCari = scanner.nextLine();
                    member.cariBuku(perpustakaan, judulCari);
                    break;
                case 4:
                	member.tampilkanBuku(perpustakaan);
                    break;
                case 5:
                    System.out.println("\n--- Menu Pinjam Buku ---");
                    System.out.print("Masukkan judul buku yang ingin dipinjam: ");
                    String judulPinjam = scanner.nextLine();
                    // PERBAIKAN: Peminjaman dilakukan oleh member
                    member.pinjamBuku(perpustakaan, judulPinjam);
                    break;
                case 6:
                    System.out.println("\n--- Menu Kembalikan Buku ---");
                    System.out.print("Masukkan judul buku yang ingin dikembalikan: ");
                    String judulKembali = scanner.nextLine();
                    member.kembalikanBuku(perpustakaan, judulKembali);
                    break;                
                case 7:
                    System.out.println("Keluar dari sistem.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Pilihan tidak valid, silakan pilih angka dari 1-7.");
            }
        }
    }

 }
