const Z_VALUE = 5000;
const WHEEL_STEP = 3;
const Z_STEP = Z_VALUE / WHEEL_STEP;



$(window).on("mousewheel", function (e) {
  console.log(e);
  const wheel = e.originalEvent.deltaY;
  //조건 걸어보기....
  if (wheel > 0) {
    //아래
    $("#itemList li").each(function (idx, item) {
      gsap.to(item, { z: `+=${Z_STEP}` });
    });
  } else {
    //위
    $("#itemList li").each(function (idx, item) {
      gsap.to(item, { z: `-=${Z_STEP}` });
    });
  }
});
