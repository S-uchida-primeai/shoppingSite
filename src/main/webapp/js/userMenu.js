document.addEventListener('DOMContentLoaded', function () {
  const slideTrack = document.querySelector('.slide-track');
  const slides = document.querySelectorAll('.slide');
  const totalSlides = slides.length;
  let currentIndex = 0;

  // スライドの幅を50%に設定
  slides.forEach(slide => {
    slide.style.flex = '0 0 50%';
  });

  // スライドトラックの幅を調整
  slideTrack.style.width = `${totalSlides * 50}%`;

  function updateSlidePosition(index) {
    slideTrack.style.transform = `translateX(-${index * 50}%)`;
  }

  function moveToNextSlide() {
    currentIndex = (currentIndex + 1) % totalSlides;
    updateSlidePosition(currentIndex);
  }

  // 初期位置の設定
  updateSlidePosition(currentIndex);

  // 4秒ごとにスライドを切り替え
  setInterval(moveToNextSlide, 4000);
});