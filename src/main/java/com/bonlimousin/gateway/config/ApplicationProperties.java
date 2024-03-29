package com.bonlimousin.gateway.config;

import javax.validation.constraints.NotBlank;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to Bon Gateway.
 * <p>
 * Properties are configured in the {@code application.yml} file.
 * See {@link io.github.jhipster.config.JHipsterProperties} for a good example.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    public enum ImageStorage {
        DISK,
        AWS
    }

	private Boolean publicAccountRegistration;
    private Thumbnails thumbnails;
    private Web web;
	private Aws aws;
	private Bff bff;

	public Boolean getPublicAccountRegistration() {
		return publicAccountRegistration;
	}

	public void setPublicAccountRegistration(Boolean publicAccountRegistration) {
		this.publicAccountRegistration = publicAccountRegistration;
	}

    public Thumbnails getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(Thumbnails thumbnails) {
        this.thumbnails = thumbnails;
    }

    public Web getWeb() {
        return web;
    }

    public void setWeb(Web web) {
        this.web = web;
    }

    public Aws getAws() {
        return aws;
    }

    public void setAws(Aws aws) {
        this.aws = aws;
    }

    public Bff getBff() {
		return bff;
	}

	public void setBff(Bff bff) {
		this.bff = bff;
	}

    public static class Thumbnails {
        private String imageBaseDir;
        private String imageStorage;
        private Boolean imageCreationAot;

        public String getImageBaseDir() {
            return imageBaseDir;
        }

        public void setImageBaseDir(String imageBaseDir) {
            this.imageBaseDir = imageBaseDir;
        }

        public ImageStorage getImageStorage() {
            return ImageStorage.valueOf(imageStorage);
        }

        public void setImageStorage(String imageStorage) {
            this.imageStorage = imageStorage;
        }

        public Boolean getImageCreationAot() {
            return imageCreationAot;
        }

        public void setImageCreationAot(Boolean imageCreationAot) {
            this.imageCreationAot = imageCreationAot;
        }
    }

    public static class Web {
        private String contentSecurityPolicy;
        private String featurePolicy;

        public String getContentSecurityPolicy() {
            return contentSecurityPolicy;
        }

        public void setContentSecurityPolicy(String contentSecurityPolicy) {
            this.contentSecurityPolicy = contentSecurityPolicy;
        }

        public String getFeaturePolicy() {
            return featurePolicy;
        }

        public void setFeaturePolicy(String featurePolicy) {
            this.featurePolicy = featurePolicy;
        }
    }

    public static class Aws {
	    private String regionName;
	    private String accessKey;
        private String secretKey;
        private String imageBucketName;

        public String getRegionName() {
            return regionName;
        }

        public void setRegionName(String regionName) {
            this.regionName = regionName;
        }

        public String getAccessKey() {
            return accessKey;
        }

        public void setAccessKey(String accessKey) {
            this.accessKey = accessKey;
        }

        public String getSecretKey() {
            return secretKey;
        }

        public void setSecretKey(String secretKey) {
            this.secretKey = secretKey;
        }

        public String getImageBucketName() {
            return imageBucketName;
        }

        public void setImageBucketName(String imageBucketName) {
            this.imageBucketName = imageBucketName;
        }
    }

	public static class Bff {
		private Client client;
		private Resource resource;

		public Client getClient() {
			return client;
		}

		public void setClient(Client client) {
			this.client = client;
		}

		public Resource getResource() {
			return resource;
		}

		public void setResource(Resource resource) {
			this.resource = resource;
		}

		public static class Client {
			@NotBlank
			private String username;
			@NotBlank
			private String password;

			public String getUsername() {
				return username;
			}

			public void setUsername(String username) {
				this.username = username;
			}

			public String getPassword() {
				return password;
			}

			public void setPassword(String password) {
				this.password = password;
			}
		}

		public static class Resource {
			// bonContentService
			private ResourceProps boncontentservicekafka;
			private ResourceProps fragment;
			private ResourceProps localized;
			private ResourceProps story;
			private ResourceProps tag;
			// bonLivestockService
			private ResourceProps bonlivestockservicekafka;
			private ResourceProps cattle;
			private ResourceProps matrilineality;
			private ResourceProps note;
			private ResourceProps pasture;
			private ResourceProps photo;
			// bonReplicaService
			private ResourceProps blup;
			private ResourceProps bonreplicaservicekafka;
			private ResourceProps bovine;
			private ResourceProps journalentry;
			private ResourceProps sourcefile;

			public ResourceProps getBoncontentservicekafka() {
				return boncontentservicekafka;
			}

			public void setBoncontentservicekafka(ResourceProps boncontentservicekafka) {
				this.boncontentservicekafka = boncontentservicekafka;
			}

			public ResourceProps getFragment() {
				return fragment;
			}

			public void setFragment(ResourceProps fragment) {
				this.fragment = fragment;
			}

			public ResourceProps getLocalized() {
				return localized;
			}

			public void setLocalized(ResourceProps localized) {
				this.localized = localized;
			}

			public ResourceProps getStory() {
				return story;
			}

			public void setStory(ResourceProps story) {
				this.story = story;
			}

			public ResourceProps getTag() {
				return tag;
			}

			public void setTag(ResourceProps tag) {
				this.tag = tag;
			}

			public ResourceProps getBonlivestockservicekafka() {
				return bonlivestockservicekafka;
			}

			public void setBonlivestockservicekafka(ResourceProps bonlivestockservicekafka) {
				this.bonlivestockservicekafka = bonlivestockservicekafka;
			}

			public ResourceProps getCattle() {
				return cattle;
			}

			public void setCattle(ResourceProps cattle) {
				this.cattle = cattle;
			}

			public ResourceProps getMatrilineality() {
				return matrilineality;
			}

			public void setMatrilineality(ResourceProps matrilineality) {
				this.matrilineality = matrilineality;
			}

			public ResourceProps getNote() {
				return note;
			}

			public void setNote(ResourceProps note) {
				this.note = note;
			}

			public ResourceProps getPasture() {
				return pasture;
			}

			public void setPasture(ResourceProps pasture) {
				this.pasture = pasture;
			}

			public ResourceProps getPhoto() {
				return photo;
			}

			public void setPhoto(ResourceProps photo) {
				this.photo = photo;
			}

			public ResourceProps getBlup() {
				return blup;
			}

			public void setBlup(ResourceProps blup) {
				this.blup = blup;
			}

			public ResourceProps getBonreplicaservicekafka() {
				return bonreplicaservicekafka;
			}

			public void setBonreplicaservicekafka(ResourceProps bonreplicaservicekafka) {
				this.bonreplicaservicekafka = bonreplicaservicekafka;
			}

			public ResourceProps getBovine() {
				return bovine;
			}

			public void setBovine(ResourceProps bovine) {
				this.bovine = bovine;
			}

			public ResourceProps getJournalentry() {
				return journalentry;
			}

			public void setJournalentry(ResourceProps journalentry) {
				this.journalentry = journalentry;
			}

			public ResourceProps getSourcefile() {
				return sourcefile;
			}

			public void setSourcefile(ResourceProps sourcefile) {
				this.sourcefile = sourcefile;
			}

			public static class ResourceProps {

				@NotBlank
				private String name;
				private String url;

				public String getName() {
					return name;
				}

				public void setName(String name) {
					this.name = name;
				}

				public String getUrl() {
					return url;
				}

				public void setUrl(String url) {
					this.url = url;
				}
			}
		}
	}
}
