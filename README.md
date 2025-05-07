# Backend Developer Task

Bu layihə Spring Boot texnologiyası əsasında hazırlanmışdır və əsas məqsədi REST API-lərin qurulması, Redis istifadə edərək məlumatların idarə olunması və Cloudinary ilə şəkil yükləmə funksionallığını nümayiş etdirməkdir. Layihə Docker konteynerlərinə uyğunlaşdırılıb və Gradle ilə qurulur.

## ✅ Xüsusiyyətlər

- RESTful API-lər (product və card idarəsi)
- Cloudinary ilə şəkil yükləmə inteqrasiyası
- Redis ilə məlumat mübadiləsi və cache
- Docker və `docker-compose` dəstəyi
- Java 21 və Spring Boot texnologiyası

## 🛠 Quraşdırma və İşlədilmə

### Tələblər

- Java 21 və ya daha yuxarı versiya
- Docker və Docker Compose
- Cloudinary və Redis hesabı (lazım olduqda)

### Layihənin başladılması

1. **Repository-ni klonlayın**
   ```bash
   git clone <repository-url>
   cd BackendDeveloperTask
   ```

2. **Layihəni build edin**
   ```bash
   ./gradlew build
   ```

3. **Docker ilə başladın**
   ```bash
   docker-compose up --build
   ```

4. **API ünvanı:**
   ```
   http://localhost:8080
   ```

## 📁 Layihə Strukturu

```
src/
├── config/               → Cloudinary konfiqurasiya faylı
├── controller/           → REST API kontrollerləri
├── dto/                  → DTO modelləri (request/response)
├── model/                → Domen modelləri
├── repository/           → Spring Data interfeysləri
├── service/              → Servis layı (biznes məntiqi)
└── BackendDeveloperTaskApplication.java  → Baş tətbiq faylı
```

## 🔌 API Endpoint-lər

- `POST /api/card` — Yeni card əlavə et
- `GET /api/product` — Məhsulların siyahısı
- `POST /api/redis/save` — Redis-ə məlumat yaz
- `GET /api/redis/get/{key}` — Redis-dən məlumat al

## 🔐 Ətraf Mühit Dəyişənləri

Cloudinary üçün aşağıdakı environment dəyişənlərini konfiqurasiya etməlisiniz:

```env
CLOUDINARY_CLOUD_NAME=bulud_adiniz
CLOUDINARY_API_KEY=api_acariniz
CLOUDINARY_API_SECRET=api_sirriniz
```

Bunları `application.properties` faylına əlavə edə və ya Docker konteynerdə environment olaraq verə bilərsiniz.

## 📜 Lisenziya

Bu layihə yalnız demo və öyrənmə məqsədləri üçün nəzərdə tutulmuşdur.
