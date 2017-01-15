package mailservice;


public class Main {
    public static class UntrustworthyMailWorker implements MailService {
        private MailService[] services;
        private RealMailService realMailService = new RealMailService();

        public UntrustworthyMailWorker(MailService[] services) {
            this.services = services;
        }

        public MailService getRealMailService() {
            return realMailService;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            Sendable result = services[0].processMail(mail);
            for (int i = 1; i < services.length; i++) {
                result = services[i].processMail(result);
            }
            return realMailService.processMail(result);
        }
    }

    public static class Spy implements MailService {
        private Logger logger;

        public Spy(Logger logger) {
            this.logger = logger;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailMessage) {
                if (mail.getFrom() == AUSTIN_POWERS | mail.getTo() == AUSTIN_POWERS) {
                    logger.log(Level.WARNING, "Detected target mail correspondence: from {0} to {1} \"{2}\"",
                            new Object[] {mail.getFrom(), mail.getTo(), ((MailMessage) mail).getMessage()});
                }
                else {
                    logger.log(Level.INFO, "Usual correspondence: from {0} to {1}",
                            new Object[] {mail.getFrom(), mail.getTo()});
                }
            }
            return mail;
        }
    }

    public static class Thief implements MailService {
        private int minVal;
        private static int allVal;

        public Thief(int minVal) {
            this.minVal = minVal;
        }

        public int getStolenValue() {
            return allVal;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailPackage) {
                if (((MailPackage) mail).getContent().getPrice() >= minVal) {
                    allVal += ((MailPackage) mail).getContent().getPrice();
                    String stolenContent = ((MailPackage) mail).getContent().getContent();
                    return new MailPackage(mail.getFrom(), mail.getTo(),
                            new Package("stones instead of " + stolenContent, 0));
                }
            }
            return mail;
        }
    }

    public static class Inspector implements MailService {

        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailPackage) {
                if (((MailPackage) mail).getContent().getContent() == BANNED_SUBSTANCE |
                        ((MailPackage) mail).getContent().getContent() == WEAPONS) {
                    throw new IllegalPackageException();
                }
                else if (((MailPackage) mail).getContent().getContent().contains("stones")) {
                    throw new StolenPackageException();
                }
            }
            return mail;
        }
    }

    public static class IllegalPackageException extends RuntimeException {
        public IllegalPackageException() {}
    }

    public static class StolenPackageException extends RuntimeException {
        public StolenPackageException() {}
    }
}
