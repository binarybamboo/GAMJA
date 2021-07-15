const multer = require('multer');
const multerS3 = require('multer-s3');
const AWS = require('aws-sdk');
const sharp = require('sharp');
const config = require('../config/config');

const s3 = new AWS.S3({
  accessKeyId: config.aws.accesskey, // 노출주의
  secretAccessKey: config.aws.secretKey, // 노출주의
  region: config.aws.region, // 노출주의
});
const upload = multer({
  // Content-Type : multipart/form-data
  // Body -> form-data
  storage: multerS3({
    s3,
    bucket: 'gamja-profile-bucket', // 버킷 이름
    contentType: multerS3.AUTO_CONTENT_TYPE, // 자동을 콘텐츠 타입 세팅
    acl: 'public-read', // 클라이언트에서 자유롭게 가용하기 위함
    transforms: () => sharp().resize(500, 500),
    key: (req, file, cb) => {
      cb(null, `${Date.now()}_image_500_500_${file.originalname}`);
    },
    size: 2 * 1024 * 1024, // 용량 제한 maybe 5MB
  }),
  limits: {
    files: 5,
    fileSize: 2 * 1024 * 1024,
  },
  fileFilter: (req, file, cb) => {
    const extname = file.originalname
      .toLowerCase()
      .match(/.(jpeg|jpg|png|gif)$/);
    const mimetype = file.mimetype.match(/(jpeg|jpg|png|gif)$/);
    if (mimetype && extname) cb(null, true);
    else cb(new Error('Extension Error')); // 확장자명 틀릴 시,
  },
});

module.exports = { s3, upload };
