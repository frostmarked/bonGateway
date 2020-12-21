import { Maybe, PictureVo, PictureSourceVo, Visibility } from '../../bonpublicgraphql/bonpublicgraphql';

export const DEFAULT_IMG = '/content/images/bon/simple-cow-logo-limousin.png';

export const DEFAULT_PICTURE = {
  id: 1,
  caption: 'image is missing',
  visibility: Visibility.RoleAnonymous,
  taken: new Date().toISOString(),
  sources: [
    {
      name: 'default-img',
      url: '/content/images/bon/simple-cow-logo-limousin.png',
      width: 100,
      height: 100,
      contentType: 'image/png',
    } as PictureSourceVo,
  ],
} as PictureVo;

export const pickPictureSourceUrl = (
  pictureSources: Array<Maybe<PictureSourceVo>>,
  targetWidth = 300,
  fallbackImage: string = DEFAULT_IMG
): string => {
  let selectedUrl = fallbackImage;
  let diff: number;
  let currentWidthDiff = 1000000;
  if (pictureSources.length) {
    pictureSources.forEach(ps => {
      if (ps && ps.width && ps.url) {
        diff = Math.abs(ps?.width - targetWidth);
        if (diff < currentWidthDiff) {
          selectedUrl = ps.url;
          currentWidthDiff = diff;
        }
      }
    });
    return selectedUrl;
  } else {
    return fallbackImage;
  }
};

export const pickFirstPictureSourceUrl = (
  pictures: Array<Maybe<PictureVo>>,
  targetWidth = 300,
  fallbackImage: string = DEFAULT_IMG
): string => {
  if (pictures && pictures[0] && pictures[0].sources) {
    return pickPictureSourceUrl(pictures && pictures[0] && pictures[0].sources, targetWidth, fallbackImage);
  } else {
    return fallbackImage;
  }
};

export const randomPictureVoFromPicsum = (picsumSeed = 'bonlim', width = 400, height = 300): PictureVo => {
  const url = `https://picsum.photos/seed/${picsumSeed}/${width}/${height}.jpg?grayscale`;
  return {
    id: 1,
    caption: picsumSeed,
    visibility: Visibility.RoleAnonymous,
    taken: new Date().toISOString(),
    sources: [
      {
        name: picsumSeed,
        url,
        width,
        height,
        contentType: 'image/jpeg',
      } as PictureSourceVo,
    ],
  } as PictureVo;
};

export const randomPictureVosFromPicsum = (minimum = 1, maximum = 3, picsumSeed = 'bonlim', width = 400, height = 300): PictureVo[] => {
  const randomnumber = Math.floor(Math.random() * (maximum - minimum + 1)) + minimum;
  const pics = [];
  for (let i = 0; i < randomnumber; i++) {
    pics.push(randomPictureVoFromPicsum(picsumSeed + '-' + i, width, height));
  }
  return pics;
};
