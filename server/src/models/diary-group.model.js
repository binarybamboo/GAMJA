const mongoose = require('mongoose');

const diaryGroupSchema = mongoose.Schema(
  {
    name: {
      type: String,
      required: false,
    },
    description: {
      type: String,
      required: true,
    },
    diary: [{ type: mongoose.Schema.Types.ObjectId, ref: 'Diary' }],
    author: {
      type: mongoose.Schema.Types.ObjectId,
      required: true,
      ref: 'User',
    },
  },
  {
    timestamps: true,
  },
);

const DiaryGroup = mongoose.model('DiaryGroup', diaryGroupSchema);

module.exports = DiaryGroup;
