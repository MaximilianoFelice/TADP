module TP

  module Singleton_Module

    attr_accessor :singleton_module

    # If the module is included by a class, it defines behaviour for every new instance
    def self.included(including_class)
      including_class.singleton_class.class_eval{attr_accessor :singleton_module
      def singleton_module
        @singleton_module ||= Module.new
      end
      }
    end

    def singleton_module
      @singleton_module ||= Module.new
    end

  end

end